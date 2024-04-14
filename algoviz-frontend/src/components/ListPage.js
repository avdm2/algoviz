import React from 'react';
import AlgorithmList from './AlgorithmList';
import DataStructureList from './DataStructureList';
import Button from '@mui/material/Button';
import { useNavigate } from 'react-router-dom';

const ListPage = () => {
  const navigate = useNavigate();

  return (
    <div style={{ padding: '20px', display: 'flex', flexDirection: 'column', alignItems: 'center' }}>
      <div style={{ display: 'flex', justifyContent: 'center', gap: '50px', marginBottom: '20px' }}>
        <AlgorithmList />
        <DataStructureList />
      </div>
      <Button onClick={() => navigate(-1)} variant="contained" color="secondary" style={{ margin: '10px' }}>Назад</Button>
    </div>
  );
};

export default ListPage;