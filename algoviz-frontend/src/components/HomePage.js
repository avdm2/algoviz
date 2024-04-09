import React from 'react';
import { Link } from 'react-router-dom';
import Button from '@mui/material/Button';

const HomePage = () => {
  return (
    <div style={{ textAlign: 'center', marginTop: '50px' }}>
      <h1>Визуализация алгоритмов и структур данных</h1>
      <div>
        <Button
          variant="contained"
          color="primary"
          component={Link}
          to="/list"
          style={{ margin: '10px' }}
        >
          Список алгоритмов и структур данных
        </Button>
        <br />
        <Button
          variant="contained"
          color="secondary"
          component={Link}
          to="/history"
          style={{ margin: '10px' }}
        >
          История визуализаций
        </Button>
      </div>
    </div>
  );
};

export default HomePage;
