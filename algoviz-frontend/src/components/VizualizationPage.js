import React from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import BinarySearchVisualization from './vizualizations/BinarySearchVizualization';
import DFSVisualization from './vizualizations/DfsVizualization';
import { Button, Typography } from '@mui/material';
import BFSVisualization from './vizualizations/BfsVizualization';
import DijkstraVizualization from './vizualizations/DijkstraVizualization';
import QuickSortVisualization from './vizualizations/QuickSortVizualization';

const VisualizationPage = () => {
  const navigate = useNavigate();
  const { simpleName } = useParams();

  const renderVisualization = () => {
    switch (simpleName) {
      case 'binary_search':
        return <BinarySearchVisualization />;
      case 'dfs':
        return <DFSVisualization />;
      case 'bfs':
        return <BFSVisualization />;
      case 'dijkstra':
        return <DijkstraVizualization />
      case 'quicksort':
        return <QuickSortVisualization />
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
      <Typography variant="h4" sx={{ ml: 2, mt: 2, fontWeight: 'bold' }}>Визуализация: {simpleName}</Typography>
      {renderVisualization()}
    </div>
  );
};

export default VisualizationPage;
