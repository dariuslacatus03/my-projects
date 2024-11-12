import React, { useEffect, useState } from 'react';
import './ExamplePage1.css';
import PropertyService from '../../service/PropertyService';

export default function ExamplePage1({ username }) {
    const [properties, setProperties] = useState([]);

    useEffect(() => {
        PropertyService.getAllProperties().then((response) => {
            setProperties(response);
        }).catch((error) => {
            console.log(error);
        });
    }, []);

    const handleAddProperty = (property) => {
        PropertyService.addPropertyToUser(localStorage.getItem("username"), property).then((response) => {
            console.log(`Property ${property.id} added to user ${username}`);
            console.log(response);
        }).catch((error) => {
            console.log(error);
        });
    };

    const handleDeleteProperty = (propertyId) => {
        PropertyService.deleteProperty(propertyId).then((response) => {
            console.log(`Property ${propertyId} deleted`);
            setProperties(properties.filter(property => property.id !== propertyId));
        }).catch((error) => {
            console.log(error);
        });
    };

    return (
        <div>
            <div className="properties-container">
                {properties.map(property => (
                    <div key={property.id} className="property-item">
                        <hr />
                        <h3>Property ID: {property.id}</h3>
                        <p>Address: {property.address}</p>
                        <p>Description: {property.description}</p>
                        <button onClick={() => handleAddProperty(property)}>Add property to itself</button>
                        <button onClick={() => handleDeleteProperty(property.id)}>Delete Property</button>
                        <hr />
                    </div>
                ))}
            </div>
        </div>
    );
}
