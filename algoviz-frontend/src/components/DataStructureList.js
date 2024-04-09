import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { Link } from 'react-router-dom';
import List from '@mui/material/List';
import ListItem from '@mui/material/ListItem';
import ListItemText from '@mui/material/ListItemText';

const DataStructureList = () => {
  const [dataStructures, setDataStructures] = useState([]);

  useEffect(() => {
    axios.get('http://localhost:8080/api/data-structures/all')
      .then(response => {
        setDataStructures(response.data);
      })
      .catch(error => {
        console.error('There was an error!', error);
      });
  }, []);

  return (
    <div>
      <h2>Структуры данных</h2>
      <List>
        {dataStructures.map((dataStructure) => (
          <ListItem 
            key={dataStructure.id} 
            button 
            component={Link} 
            to={`/details/data-structure/${dataStructure.simpleName}`}
          >
            <ListItemText primary={dataStructure.name} secondary={`Сложность: ${dataStructure.complexity}`} />
          </ListItem>
        ))}
      </List>
    </div>
  );
};

export default DataStructureList;
