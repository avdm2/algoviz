import React from 'react';
import { Button } from '@mui/material';
import { useNavigate } from 'react-router-dom';

const VisualizeButton = ({ type, simpleName }) => {
  const navigate = useNavigate();

  const handleVisualize = () => {
    navigate(`/visualize/${type}/${simpleName}`);
  };

  return (
    <Button variant="contained" color="primary" onClick={handleVisualize}>
      Визуализировать
    </Button>
  );
};

export default VisualizeButton;
