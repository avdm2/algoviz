import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { Link } from 'react-router-dom';
import List from '@mui/material/List';
import ListItem from '@mui/material/ListItem';
import ListItemText from '@mui/material/ListItemText';

const AlgorithmList = () => {
  const [algorithms, setAlgorithms] = useState([]);

  useEffect(() => {
    axios.get('http://localhost:8080/api/algorithms/all')
      .then(response => {
        setAlgorithms(response.data);
      })
      .catch(error => {
        console.error('There was an error!', error);
      });
  }, []);

  return (
    <div>
      <h2>Алгоритмы</h2>
      <List>
        {algorithms.map((algorithm) => (
        <ListItem 
          key={algorithm.id} 
          button 
          component={Link} 
          to={`/details/algorithm/${algorithm.simpleName}`}
        >
            <ListItemText primary={algorithm.name} secondary={`Сложность: ${algorithm.complexity}`} />
          </ListItem>
        ))}
      </List>
    </div>
  );
};

export default AlgorithmList;
