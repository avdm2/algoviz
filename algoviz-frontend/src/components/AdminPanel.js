import React, { useState } from 'react';
import axios from 'axios';
import { TextField, Button, Typography, Box } from '@mui/material';

const AdminPanel = () => {
    const baseURL = 'http://localhost:8080';
    const [form, setForm] = useState({
        simpleName: '',
        name: '',
        complexity: '',
        description: '',
        additionalInfo: '',
        sourceCodeJava: '',
        sourceCodePython: '',
        sourceCodeCpp: ''
    });

    const handleInputChange = (e) => {
        const { name, value } = e.target;
        setForm(prevState => ({
            ...prevState,
            [name]: value
        }));
    };

    const createOrUpdateData = (endpoint, method = 'post') => {
        axios({
            method: method,
            url: `${baseURL}${endpoint}`,
            data: form
        })
            .then(response => alert('Success: ' + JSON.stringify(response.data)))
            .catch(error => alert('Error: ' + error));
    };

    const deleteData = (endpoint) => {
        axios.delete(`${baseURL}${endpoint}`)
            .then(response => alert('Deleted: ' + response.data))
            .catch(error => alert('Error: ' + error));
    };

    return (
        <Box padding={2}>
            <Typography variant="h4" gutterBottom>Admin Panel</Typography>
            <TextField label="Simple Name" variant="outlined" name="simpleName" value={form.simpleName} onChange={handleInputChange} fullWidth margin="normal" />
            <TextField label="Name" variant="outlined" name="name" value={form.name} onChange={handleInputChange} fullWidth margin="normal" />
            <TextField label="Complexity" variant="outlined" name="complexity" value={form.complexity} onChange={handleInputChange} fullWidth margin="normal" />
            <TextField label="Description" variant="outlined" name="description" value={form.description} onChange={handleInputChange} fullWidth margin="normal" />
            <TextField label="Additional Info" variant="outlined" name="additionalInfo" value={form.additionalInfo} onChange={handleInputChange} fullWidth margin="normal" />
            <TextField label="Source Code Java" variant="outlined" name="sourceCodeJava" value={form.sourceCodeJava} onChange={handleInputChange} fullWidth margin="normal" />
            <TextField label="Source Code Python" variant="outlined" name="sourceCodePython" value={form.sourceCodePython} onChange={handleInputChange} fullWidth margin="normal" />
            <TextField label="Source Code C++" variant="outlined" name="sourceCodeCpp" value={form.sourceCodeCpp} onChange={handleInputChange} fullWidth margin="normal" />

            <Box mt={2} display="flex" justifyContent="space-between">
                <Button onClick={() => createOrUpdateData('/api/data-structures', 'post')} variant="contained" color="primary">Create Data Structure</Button>
                <Button onClick={() => createOrUpdateData(`/api/data-structures/${form.simpleName}`, 'put')} variant="contained" color="primary">Update Data Structure</Button>
                <Button onClick={() => deleteData(`/api/data-structures/${form.simpleName}`)} variant="contained" color="secondary">Delete Data Structure</Button>
                <Button onClick={() => createOrUpdateData('/api/algorithms', 'post')} variant="contained" color="primary">Create Algorithm</Button>
                <Button onClick={() => createOrUpdateData(`/api/algorithms/${form.simpleName}`, 'put')} variant="contained" color="primary">Update Algorithm</Button>
                <Button onClick={() => deleteData(`/api/algorithms/${form.simpleName}`)} variant="contained" color="secondary">Delete Algorithm</Button>
            </Box>
        </Box>
    );
};

export default AdminPanel;
