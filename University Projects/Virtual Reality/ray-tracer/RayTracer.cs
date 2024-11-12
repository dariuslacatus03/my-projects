using System;

namespace rt
{
    class RayTracer
    {
        private Geometry[] geometries;
        private Light[] lights;

        public RayTracer(Geometry[] geometries, Light[] lights)
        {
            this.geometries = geometries;
            this.lights = lights;
        }

        private double ImageToViewPlane(int n, int imgSize, double viewPlaneSize)
        {
            return -n * viewPlaneSize / imgSize + viewPlaneSize / 2;
        }

        private Intersection FindFirstIntersection(Line ray, double minDist, double maxDist)
        {
            var intersection = Intersection.NONE;

            foreach (var geometry in geometries)
            {
                var intr = geometry.GetIntersection(ray, minDist, maxDist);

                if (!intr.Valid || !intr.Visible) continue;

                if (!intersection.Valid || !intersection.Visible)
                {
                    intersection = intr;
                }
                else if (intr.T < intersection.T)
                {
                    intersection = intr;
                }
            }

            return intersection;
        }

        private bool IsLit(Vector point, Light light)
        {
            var line = new Line(light.Position, point);
            var intersection = FindFirstIntersection(line, 0, 1000000);
            if (!intersection.Valid || !intersection.Visible)
                return true;
            return intersection.T > (light.Position - point).Length() - 0.001;
        }


        public void Render(Camera camera, int width, int height, string filename)
        {
            var background = new Color(0.2, 0.2, 0.2, 1.0);
            var image = new Image(width, height);

            for (var i = 0; i < width; i++)
            {
                for (var j = 0; j < height; j++)
                {
                    var x0 = camera.Position;
                    var x1 = camera.Position +
                             camera.Direction * camera.ViewPlaneDistance +
                             camera.Up * ImageToViewPlane(j, height, camera.ViewPlaneHeight) +
                             (camera.Up ^ camera.Direction) * ImageToViewPlane(i, width, camera.ViewPlaneWidth);

                    var sightRay = new Line(x0, x1);
                    var intersection = FindFirstIntersection(sightRay, camera.FrontPlaneDistance, camera.BackPlaneDistance);

                    if (intersection.Valid && intersection.Visible)
                    {
                        var color = new Color();

                        foreach (Light light in lights)
                        {
                            var colorFromLight = new Color();
                            colorFromLight += intersection.Geometry.Material.Ambient * light.Ambient;
                            if (IsLit(intersection.Position, light))
                            {
                                var N = intersection.Normal; // Normal at the intersection
                                var E = (camera.Position - intersection.Position).Normalize();

                                // Ambient component
                                color += intersection.Material.Ambient * light.Ambient;

                                // Diffuse component
                                var L = (light.Position - intersection.Position).Normalize();
                                if (N * L > 0)
                                {
                                    colorFromLight += intersection.Geometry.Material.Diffuse * light.Diffuse * (N * L);
                                }

                                // Specular component
                                var R = (N * (N * L) * 2 - L).Normalize();
                                if (E * R > 0)
                                {
                                    colorFromLight += intersection.Geometry.Material.Specular * light.Specular *
                                                      Math.Pow(E * R, intersection.Geometry.Material.Shininess);
                                }
                                colorFromLight *= light.Intensity;
                            }
                            color += colorFromLight;
                        }
                        image.SetPixel(i, j, color);
                    }
                    else
                    {
                        image.SetPixel(i, j, background);
                    }
                }
            }

            image.Store(filename);
        }

    }
}