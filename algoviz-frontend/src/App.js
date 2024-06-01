import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import HomePage from './components/HomePage';
import ListPage from './components/ListPage';
import DetailsPane from './components/DetailsPane';
import VisualizationPage from './components/VizualizationPage';
import VisualizationHistory from './components/history/VizualizationHistory';
import AdminPanel from './components/AdminPanel';

function App() {
  return (
    <Router>
      <div>
        <Routes>
          <Route path="/" element={<HomePage />} />
          <Route path="/list" element={<ListPage />} />
          <Route path="/history" element={<VisualizationHistory />} />
          <Route path="/details/:type/:simpleName" element={<DetailsPane />} />
          <Route path="/visualize/:type/:simpleName" element={<VisualizationPage />} />
          <Route path="/admin" element={<AdminPanel />} />
        </Routes>
      </div>
    </Router>
  );
}

export default App;
