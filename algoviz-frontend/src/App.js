import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import HomePage from './components/HomePage';
import ListPage from './components/ListPage';
import DetailsPane from './components/DetailsPane';

function App() {
  return (
    <Router>
      <div>
        <Routes>
          <Route path="/" element={<HomePage />} />
          <Route path="/list" element={<ListPage />} />
          <Route path="/details/:type/:simpleName" element={<DetailsPane />} />
        </Routes>
      </div>
    </Router>
  );
}

export default App;
