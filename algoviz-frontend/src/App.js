import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import HomePage from './components/HomePage';
import ListPage from './components/ListPage';
import DetailsPane from './components/DetailsPane';
import VisualizationPage from './components/VizualizationPage';

function App() {
  return (
    <Router>
      <div>
        <Routes>
          <Route path="/" element={<HomePage />} />
          <Route path="/list" element={<ListPage />} />
          <Route path="/details/:type/:simpleName" element={<DetailsPane />} />
          <Route path="/visualize/:type/:simpleName" element={<VisualizationPage />} />
        </Routes>
      </div>
    </Router>
  );
}

export default App;
