import React, { useState } from 'react';
import './ExamplePage2.css';

const getRandomColor = () => {
    const letters = '0123456789ABCDEF';
    let color = '#';
    for (let i = 0; i < 6; i++) {
        color += letters[Math.floor(Math.random() * 16)];
    }
    return color;
};

export default function ExamplePage2(){
    const [color, setColor] = useState(getRandomColor());

    const handleClick = () => {
        setColor(getRandomColor());
    };

    return (
        <div className="random-color-container" style={{ backgroundColor: color }}>
            <h1>Random Color</h1>
            <p>{color}</p>
            <button onClick={handleClick}>Get Another Color</button>
        </div>
    );
};
