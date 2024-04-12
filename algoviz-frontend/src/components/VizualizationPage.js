import React, { useState } from 'react';
import { useParams } from 'react-router-dom';
import BinarySearchVisualization from './BinarySearchVizualization';

const VisualizationPage = () => {
  const { type, simpleName } = useParams();

  const renderVisualization = () => {
    switch (simpleName) {
      case 'binary_search':
        return <BinarySearchVisualization />;
      default:
        return <p>Визуализация для этого элемента еще не реализована.</p>;
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
