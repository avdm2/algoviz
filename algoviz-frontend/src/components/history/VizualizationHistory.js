import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { Button, Typography, Box, Paper, List, ListItem, ListItemText, Input } from '@mui/material';

const VisualizationHistory = () => {
    const [history, setHistory] = useState(JSON.parse(localStorage.getItem('visualizationHistory')) || []);
    const navigate = useNavigate();

    const handleImport = (event) => {
        const fileReader = new FileReader();
        fileReader.readAsText(event.target.files[0], "UTF-8");
        fileReader.onload = e => {
            const content = e.target.result;
            if (!content) {
                alert('Файл пуст');
                return;
            }
            try {
                const importedHistory = JSON.parse(content);
                setHistory(importedHistory);
                localStorage.setItem('visualizationHistory', JSON.stringify(importedHistory));
            } catch (error) {
                alert('Ошибка при чтении файла');
            }
        };
    };

    const handleClearHistory = () => {
        setHistory([]);
        localStorage.removeItem('visualizationHistory');
    };

    const handleExport = () => {
        if (history.length === 0) {
            alert('История пуста');
            return;
        }
        const dataStr = JSON.stringify(history);
        const dataUri = 'data:application/json;charset=utf-8,' + encodeURIComponent(dataStr);
        const exportFileDefaultName = 'visualizationHistory.json';
        const linkElement = document.createElement('a');
        linkElement.setAttribute('href', dataUri);
        linkElement.setAttribute('download', exportFileDefaultName);
        linkElement.click();
    };

    const handleFileInputClick = () => {
        document.getElementById('file-input').click();
    };

    const formatParams = (params) => {
        return Array.isArray(params)
            ? params.map(([key, value], index) => (
                <React.Fragment key={index}>
                    {key}: {value}
                    <br />
                </React.Fragment>
            ))
            : null;
    };

    return (
        <Box sx={{ width: '100%', display: 'flex', flexDirection: 'column', alignItems: 'center', padding: 4 }}>
            <Typography variant="h4" sx={{ mb: 2 }}>История визуализаций</Typography>
            {history.length > 0 ? (
                <Paper sx={{ width: '100%', maxWidth: 360, bgcolor: 'background.paper' }}>
                    <List>
                        {history.map((item, index) => {
                            const date = new Date(item.timestamp);

                            const formattedDate = new Intl.DateTimeFormat('ru-RU', {
                                year: 'numeric',
                                month: '2-digit',
                                day: '2-digit',
                                hour: '2-digit',
                                minute: '2-digit'
                            }).format(date);

                            return (
                                <ListItem button key={index} onClick={() => navigate(`/visualize/${item.type}/${item.simpleName}`)}>
                                    <ListItemText
                                        primary={item.fullName}
                                        secondary={
                                            <>
                                                {formattedDate}
                                                <br />
                                                {item.params ? formatParams(item.params) : null}
                                            </>
                                        }
                                    />
                                </ListItem>
                            );
                        })}
                    </List>
                </Paper>
            ) : (
                <Typography>История визуализаций пуста.</Typography>
            )}
            <Box sx={{ mt: 2, display: 'flex', justifyContent: 'center', gap: 1 }}>
                <Button
                    variant="contained"
                    onClick={handleFileInputClick}
                    sx={{ width: '100px' }}
                >
                    Импорт
                    <Input
                        id="file-input"
                        type="file"
                        hidden
                        onChange={handleImport}
                        style={{ visibility: 'hidden' }}
                    />
                </Button>
                <Button variant="contained" onClick={handleExport} sx={{ flexGrow: 1 }}>
                    Экспорт
                </Button>
                <Button variant="contained" onClick={handleClearHistory} color="warning" sx={{ flexGrow: 1 }}>
                    Очистить историю
                </Button>

            </Box>
            <Box sx={{ mt: 2, display: 'flex', justifyContent: 'center', gap: 1 }}>
                <Button onClick={() => navigate(-1)} variant="contained" color="secondary" sx={{ flexGrow: 1 }}>
                    Назад
                </Button>
            </Box>
        </Box>
    );
};

export default VisualizationHistory;
