import React, { useState } from 'react';
import { BrowserRouter as Router, Route, Routes, Navigate } from 'react-router-dom';
import LoginForm from './components/LoginForm/LoginForm';
import './App.css';
import ExamplePage1 from './components/ExamplePage1/ExamplePage1';
import ExamplePage2 from './components/ExamplePage2/ExamplePage2';
import LogOut from './components/LogOut/LogOut';

function App() {
  const [username, setUsername] = useState('');
  
  
  return (
    <div className="App">
      <Router>
          <Routes>
            <Route path="/login" element={<LoginForm username={username} setUsername={setUsername}/>} />

            <Route path="/example-page1" element={
                localStorage.getItem("authToken") === "good_answer" ? (
                    <ExamplePage1 username={username}/>
                ) : (
                    <Navigate to="/login" />
                ) } />

            <Route path="/example-page2" element={
                localStorage.getItem("authToken") === "good_answer" ? (
                    <ExamplePage2 />
                ) : (
                    <Navigate to="/login" />
                ) } />

            <Route path="/logout" element={
                localStorage.getItem("authToken") === "good_answer" ? (
                  <LogOut />
                ) : (
                  <Navigate to={"/login"} />
                ) } />

            <Route path="/" element={<Navigate to="/login" />} />

            {/* Add other routes here if needed */}
          </Routes>
      </Router>
    </div>
    
  );
}

export default App;
