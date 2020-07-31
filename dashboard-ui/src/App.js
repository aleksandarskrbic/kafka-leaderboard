import React, { useState, useEffect } from 'react';
import axios from 'axios';
import Dashboard from './Dashboard'
import './App.css';

function App() {
  const [state, setState] = useState([])

  useEffect(() => {
    axios.get('http://localhost:8080/api/aggregate').then(res => setState(res.data));
  }, [])

  return (
    <Dashboard state={state}/>
  );
}

export default App;
