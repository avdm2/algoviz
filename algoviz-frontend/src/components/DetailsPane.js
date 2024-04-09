import React, { useState, useEffect } from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import axios from 'axios';
import { Tabs, Tab, Button } from '@mui/material';
import SourceCodeTab from './SourceCodeTab';

const DetailsPane = () => {
  const { type, simpleName } = useParams();
  const navigate = useNavigate();
  const [activeTab, setActiveTab] = useState(0);
  const [detail, setDetail] = useState({ description: 'Загрузка...', name: '' });
  const [info, setInfo] = useState('Информация загружается...');

  const fetchDetail = async () => {
    try {
      const apiUrl = type === 'algorithm' ? `http://localhost:8080/api/algorithms/${simpleName}` : `http://localhost:8080/api/data-structures/${simpleName}`;
      const detailResponse = await axios.get(apiUrl);
      setDetail(detailResponse.data);
    } catch {
      setDetail({ description: 'Нет информации' });
    }

    try {
      const infoFileName = type === 'algorithm' ? 'algorithms_info' : 'data_structures_info';
      const infoResponse = await fetch(`/${infoFileName}/${simpleName}_info.txt`);
      if (!infoResponse.ok) throw new Error();
      const infoText = await infoResponse.text();
      setInfo(infoText);
    } catch {
      setInfo('Нет информации');
    }
  };

  useEffect(() => {
    fetchDetail();
  }, [type, simpleName]);

  const handleChange = (event, newValue) => {
    setActiveTab(newValue);
  };

  return (
    <div>
      <Tabs value={activeTab} onChange={handleChange}>
        <Tab label="Общее" />
        <Tab label="Исходный код" />
      </Tabs>
      {activeTab === 0 && (
        <div>
          <h3>Описание</h3>
          <p>{detail.description}</p>
          <h3>Дополнительная информация</h3>
          <p>{info}</p>
        </div>
      )}
      {activeTab === 1 && <SourceCodeTab simpleName={simpleName} />}
      <Button onClick={() => navigate(-1)} style={{ marginTop: '10px' }}>
        Назад
      </Button>
    </div>
  );
};

export default DetailsPane;
