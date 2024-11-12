import React, { useEffect, useState } from 'react';
import './LoginForm.css';
import AuthenticationService from '../../service/AuthenticationService';
import { useNavigate } from 'react-router-dom';

export default function LoginForm({username, setUsername}) {
    const [error, setError] = useState('');
    const [secretQuestion, setSecretQuestion] = useState('');
    const [secretAnswer, setSecretAnswer] = useState('');
    const navigate = useNavigate();
    const [loggedIn, setLoggedIn] = useState(false);

    useEffect(() => {
        if (loggedIn === true) {
            navigate('/example-page1');
        }
      }, [loggedIn])

    const handleLogIn = () => {
        AuthenticationService.login(username).then((response) => {
            setSecretQuestion(response.secretQuestion);
            setError("");
        }).catch((error) => {
            setError(error);
        });
    };

    const handleSubmitSecretAnswer = () => {
        AuthenticationService.verifySecretAnswer(username, secretAnswer).then((response) => {
            localStorage.setItem("authToken", response.token);
            localStorage.setItem("username", username);
            setError("");
            setLoggedIn(true);
        }).catch((error) => {
            setError(error);
        });
    };

    return (
        <div className="login-container">
            <h2>Login</h2>
            {error && <p className="error">{error}</p>}
            <label htmlFor="username">Username</label>
            <input 
                type="text" 
                id="username" 
                name="username" 
                value={username}
                onChange={(e) => setUsername(e.target.value)}
                required 
            />
            
            {!secretQuestion && (
                <button onClick={handleLogIn}>Log in</button>
            )}

            {secretQuestion && (
                <>
                    <label htmlFor="secretAnswer">{secretQuestion}</label>
                    <input 
                        type="text" 
                        id="secretAnswer" 
                        name="secretAnswer" 
                        value={secretAnswer}
                        onChange={(e) => setSecretAnswer(e.target.value)}
                        required 
                    />
                    <button onClick={handleSubmitSecretAnswer}>Submit Answer</button>
                </>
            )}
        </div>
    );
}
