import React from 'react';
import AlgorithmList from './AlgorithmList';
import DataStructureList from './DataStructureList';
import Button from '@mui/material/Button';
import { Link } from 'react-router-dom';

const ListPage = () => {
  return (
    <div style={{ padding: '20px', display: 'flex', flexDirection: 'column', alignItems: 'center' }}>
      <div style={{ display: 'flex', justifyContent: 'center', gap: '50px', marginBottom: '20px' }}>
        <AlgorithmList />
        <DataStructureList />
      </div>
      <Button variant="contained" component={Link} to="/" style={{ marginTop: '20px' }}>
        Назад
      </Button>
    </div>
  );
};

export default ListPage;