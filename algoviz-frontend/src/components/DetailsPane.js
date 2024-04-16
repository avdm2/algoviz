import React, { useState, useEffect } from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import axios from 'axios';
import { Tabs, Tab, Button } from '@mui/material';
import SourceCodeTab from './SourceCodeTab';
import VisualizeButton from './VizualizeButton';

const DetailsPane = () => {
  const { type, simpleName } = useParams();
  const navigate = useNavigate();
  const [activeTab, setActiveTab] = useState(0);
  const [detail, setDetail] = useState({ description: 'Загрузка...', name: '', additionalInfo: '', sourceCodeJava: '', sourceCodePython: '', sourceCodeCpp: '' });

  const allowedVisualizations = new Set(['bfs', 'dfs', 'binary_search', 'quicksort', 'dijkstra']);

  const fetchDetail = async () => {
    try {
      const apiUrl = type === 'algorithm' ? `https://algoviz-backend-avdm2.amvera.io/api/algorithms/${simpleName}` : `https://algoviz-backend-avdm2.amvera.io/api/data-structures/${simpleName}`;
      const { data } = await axios.get(apiUrl);
      setDetail(data);
    } catch {
      setDetail(prev => ({ ...prev, description: 'Нет информации' }));
    }
  };

  useEffect(() => {
    fetchDetail();
  }, [type, simpleName]);

  const handleChange = (event, newValue) => {
    setActiveTab(newValue);
  };

  const formatMultilineText = (text) => {
    return text.split('\n').map((str, index, array) => (
      <span key={index}>
        {str}
        {index !== array.length - 1 && <br />}
      </span>
    ));
  };


  return (
    <div style={{ padding: '20px', display: 'flex', flexDirection: 'column', alignItems: 'center' }}>
      <Tabs value={activeTab} onChange={handleChange} centered>
        <Tab label="Общее" />
        <Tab label="Исходный код" />
      </Tabs>
      {activeTab === 0 && (
        <div>
          <h3>Описание</h3>
          <p>{detail.description}</p>
          <h3>Дополнительная информация</h3>
          <p>{detail.additionalInfo ? formatMultilineText(detail.additionalInfo) : 'Нет информации'}</p>
        </div>
      )}
      {activeTab === 1 && <SourceCodeTab detail={detail} />}
      <div style={{ display: 'flex', justifyContent: 'center', marginTop: '20px' }}>
        {allowedVisualizations.has(simpleName) && (
          <VisualizeButton simpleName={simpleName} style={{ marginRight: '10px' }} />
        )}
        <Button onClick={() => navigate(-1)} variant="contained" color="secondary" style={{ margin: '10px' }}>
          Назад
        </Button>
      </div>
    </div>
  );
};

export default DetailsPane;
