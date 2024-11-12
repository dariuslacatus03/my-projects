import React from 'react';
import { useNavigate } from 'react-router-dom';

export default function LogOut(){
    const navigate = useNavigate();

    const handleLogout = () => {
        localStorage.removeItem('authToken');
        navigate("/login");    
    };

    return (
        <div className="logout-confirmation">
            <p>Are you sure you want to log out?</p>
            <button onClick={handleLogout}>Logout</button>
        </div>
    );
};

