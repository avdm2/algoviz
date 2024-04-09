import React, { useState, useEffect } from 'react';
import { Button } from '@mui/material';

const languages = ['cpp', 'java', 'python'];

const SourceCodeTab = ({ simpleName }) => {
  const [sourceCode, setSourceCode] = useState('Информация загружается...');
  const [activeLanguage, setActiveLanguage] = useState(languages[0]);

  useEffect(() => {
    console.log(simpleName)
    fetch(`/source_code/${simpleName}/${simpleName}_${activeLanguage}.txt`)
      .then(response => {
        if (!response.ok) throw new Error();
        return response.text();
      })
      .then(text => {
        setSourceCode(text);
      })
      .catch(() => {
        setSourceCode('Нет информации');
      });
  }, [simpleName, activeLanguage]);

  return (
    <div>
      {languages.map(language => (
        <Button
          key={language}
          onClick={() => setActiveLanguage(language)}
          disabled={activeLanguage === language}
        >
          {language.toUpperCase()}
        </Button>
      ))}
      <pre>{sourceCode}</pre>
    </div>
  );
};

export default SourceCodeTab;
