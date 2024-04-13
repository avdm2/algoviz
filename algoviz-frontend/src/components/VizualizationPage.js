import React from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import BinarySearchVisualization from './vizualizations/BinarySearchVizualization';
import DFSVisualization from './vizualizations/DfsVizualization';
import { Button } from '@mui/material';
import BFSVisualization from './vizualizations/BfsVizualization';

const VisualizationPage = () => {
  const navigate = useNavigate();
  const { type, simpleName } = useParams();

  const renderVisualization = () => {
    switch (simpleName) {
      case 'binary_search':
        return <BinarySearchVisualization />;
      case 'dfs':
        return <DFSVisualization />;
      case 'bfs':
        return <BFSVisualization />;
      default:
        return (
          <div>
            <p>
              Визуализация для этого элемента еще не реализована.
            </p>
            <Button onClick={() => navigate(-1)} variant="contained" color="secondary" style={{ margin: '10px' }}>
              Назад
            </Button>
          </div>
        );
    }
  };

  return (
    <div>
      <h1>Визуализация: {simpleName}</h1>
      {renderVisualization()}
    </div>
  );
};

export default VisualizationPage;
