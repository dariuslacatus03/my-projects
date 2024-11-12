using System;


namespace rt
{
    public class Ellipsoid : Geometry
    {
        public Vector Center { get; }
        private Vector SemiAxesLength { get; }
        private double Radius { get; }
        
        
        public Ellipsoid(Vector center, Vector semiAxesLength, double radius, Material material, Color color) : base(material, color)
        {
            Center = center;
            SemiAxesLength = semiAxesLength;
            Radius = radius;
        }

        public Ellipsoid(Vector center, Vector semiAxesLength, double radius, Color color) : base(color)
        {
            Center = center;
            SemiAxesLength = semiAxesLength;
            Radius = radius;
        }

        public override Intersection GetIntersection(Line line, double minDist, double maxDist)
        {
            var A = (line.Dx.X * line.Dx.X) / (SemiAxesLength.X * SemiAxesLength.X)
                + (line.Dx.Y * line.Dx.Y) / (SemiAxesLength.Y * SemiAxesLength.Y)
                + (line.Dx.Z * line.Dx.Z) / (SemiAxesLength.Z * SemiAxesLength.Z);

            var B = 2 * (
                ((line.X0.X - Center.X) * line.Dx.X) / (SemiAxesLength.X * SemiAxesLength.X)
                + ((line.X0.Y - Center.Y) * line.Dx.Y) / (SemiAxesLength.Y * SemiAxesLength.Y)
                + ((line.X0.Z - Center.Z) * line.Dx.Z) / (SemiAxesLength.Z * SemiAxesLength.Z)
                );

            var C = (line.X0.X - Center.X) * (line.X0.X - Center.X) / (SemiAxesLength.X * SemiAxesLength.X)
                    + (line.X0.Y - Center.Y) * (line.X0.Y - Center.Y) / (SemiAxesLength.Y * SemiAxesLength.Y)
                    + (line.X0.Z - Center.Z) * (line.X0.Z - Center.Z) / (SemiAxesLength.Z * SemiAxesLength.Z);

            var discriminant = B * B - 4 * A * (C - Radius * Radius);
            if (discriminant < 0)
            {
                return new Intersection();
            }

            var t1 = (-B - Math.Sqrt(discriminant)) / (2 * A);
            var t2 = (-B + Math.Sqrt(discriminant)) / (2 * A);

            var valid1 = t1 >= minDist && t1 <= maxDist;
            var valid2 = t2 >= minDist && t2 <= maxDist;

            if (!valid1 && !valid2)
                return new Intersection();

            double t;
            if (valid1 && valid2)
            {
                t = Math.Min(t1, t2);
            }
            else
            {
                t = valid1 ? t1 : t2;
            }

            var intersectionPoint = line.CoordinateToPosition(t);
            var normal = new Vector(
                (intersectionPoint.X - Center.X) / (SemiAxesLength.X * SemiAxesLength.X),
                (intersectionPoint.Y - Center.Y) / (SemiAxesLength.Y * SemiAxesLength.Y),
                (intersectionPoint.Z - Center.Z) / (SemiAxesLength.Z * SemiAxesLength.Z)
            ).Normalize();

            return new Intersection(true, true, this, line, t, normal, Material, Color);
        }

    }
}
