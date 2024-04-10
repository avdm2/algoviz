import React from 'react';
import { Button } from '@mui/material';

const languages = {
  cpp: 'sourceCodeCpp',
  java: 'sourceCodeJava',
  python: 'sourceCodePython',
};

const SourceCodeTab = ({ detail }) => {
  const [activeLanguage, setActiveLanguage] = React.useState('cpp');

  const sourceCode = detail[languages[activeLanguage]] || 'Информация загружается...';

  return (
    <div style={{ padding: '20px' }}>
      {Object.keys(languages).map(language => (
        <Button
          key={language}
          onClick={() => setActiveLanguage(language)}
          disabled={activeLanguage === language}
          style={{ margin: '0 10px', backgroundColor: activeLanguage === language ? 'grey' : '#1976d2', color: 'white' }}
        >
          {language.toUpperCase()}
        </Button>
      ))}
      <pre style={{ whiteSpace: 'pre-wrap', wordBreak: 'break-word' }}>{sourceCode}</pre>
    </div>
  );
};

export default SourceCodeTab;
