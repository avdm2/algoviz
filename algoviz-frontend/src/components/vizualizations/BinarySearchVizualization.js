import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { Button, TextField, Box, Typography } from '@mui/material';

const BinarySearchVisualization = () => {
    const [arrayInput, setArrayInput] = useState('');
    const [targetInput, setTargetInput] = useState('');
    const [errors, setErrors] = useState({ arrayError: false, targetError: false });
    const [steps, setSteps] = useState([]);
    const [currentStep, setCurrentStep] = useState(null);
    const navigate = useNavigate();

    const handleArrayChange = (event) => {
        setArrayInput(event.target.value);
        const numbers = event.target.value.split(',').map(Number);
        const arrayError = !numbers.every((val, i, arr) => !i || val >= arr[i - 1]);
        setErrors(prev => ({ ...prev, arrayError }));
    };

    const handleTargetChange = (event) => {
        setTargetInput(event.target.value);
        const target = Number(event.target.value);
        const targetError = isNaN(target);
        setErrors(prev => ({ ...prev, targetError }));
    };

    const saveVisualizationToHistory = () => {
        const history = JSON.parse(localStorage.getItem('visualizationHistory')) || [];
        history.push({
            type: 'algorithm',
            simpleName: 'binary_search',
            fullName: 'Бинарный поиск',
            timestamp: new Date().toISOString(),
            params: Array.from(new Map([
                ["Массив", arrayInput],
                ["Искомое число", targetInput]
            ]).entries()),
            link: 'http://localhost:3000/visualize/algorithm/binary_search'
        });
        localStorage.setItem('visualizationHistory', JSON.stringify(history));
    };

    const binarySearch = (arr, target) => {
        let start = 0;
        let end = arr.length - 1;
        let middle;
        let stepCounter = 0;
        const newSteps = [];

        while (start <= end) {
            middle = Math.floor((start + end) / 2);
            newSteps.push({ arr: [...arr], start, middle, end, step: stepCounter++ });

            if (arr[middle] === target) {
                return newSteps;
            } else if (arr[middle] < target) {
                start = middle + 1;
            } else {
                end = middle - 1;
            }
        }

        return newSteps;
    };

    const handleVisualize = () => {
        if (errors.arrayError || errors.targetError) {
            alert('Исправьте ошибки перед запуском визуализации.');
            return;
        }

        saveVisualizationToHistory();
        setCurrentStep(0);
        const array = arrayInput.split(',').map(Number);
        const target = Number(targetInput);
        const newSteps = binarySearch(array, target);
        setSteps(newSteps);
        setCurrentStep(0);
    };

    const defineColor = (index, start, middle, end) => {
        let backgroundColor;
        if (index === middle) {
            backgroundColor = 'lightgreen';
        } else if (index === start) {
            backgroundColor = 'lightblue';
        } else if (index === end) {
            backgroundColor = 'pink';
        } else if (index > start && index < end) {
            backgroundColor = '#F4DECB';
        } else {
            backgroundColor = 'none';
        }

        return backgroundColor;
    };

    const ArrayVisualization = ({ step }) => {
        if (!step) return null;
        const { arr, start, middle, end } = step;

        const elements = arr.map((num, index) => (
            <div
                key={index}
                style={{
                    width: '50px',
                    border: '1px solid black',
                    display: 'inline-block',
                    padding: '5px',
                    backgroundColor: defineColor(index, start, middle, end),
                }}
            >
                {num}
            </div>
        ));
        return <div>{elements}</div>;
    };

    return (
        <Box padding={2}>
            <TextField
                label="Массив (через запятую)"
                variant="outlined"
                value={arrayInput}
                onChange={handleArrayChange}
                error={errors.arrayError}
                helperText={errors.arrayError ? "Массив должен быть отсортирован и содержать только числовые значения!" : ""}
                fullWidth
                margin="normal"
            />
            <TextField
                label="Цель поиска"
                variant="outlined"
                value={targetInput}
                onChange={handleTargetChange}
                error={errors.targetError}
                helperText={errors.targetError ? "Цель должна быть числом." : ""}
                fullWidth
                margin="normal"
            />
            <Button
                variant="contained"
                onClick={handleVisualize}
                disabled={errors.arrayError || errors.targetError}
                color="primary"
                style={{ margin: '10px' }}
                fullWidth
            >
                Запустить визуализацию
            </Button>
            <Button onClick={() => navigate(-1)} variant="contained" color="secondary" style={{ margin: '10px' }}>
                Назад
            </Button>
            {currentStep !== null && (
                <>
                    <Typography variant="h6">Шаг: {currentStep + 1}</Typography>
                    <ArrayVisualization step={steps[currentStep]} />
                    <Box display="flex" justifyContent="space-between" alignItems="center">
                        <Button
                            variant="contained"
                            onClick={() => setCurrentStep((prev) => (prev + 1 < steps.length ? prev + 1 : prev))}
                            disabled={currentStep + 1 === steps.length}
                            style={{ margin: '10px' }}
                        >
                            Следующий шаг
                        </Button>
                        <Box>
                            <Typography display="inline" sx={{ backgroundColor: 'lightblue', m: 1 }}>Start</Typography>
                            <Typography display="inline" sx={{ backgroundColor: 'lightgreen', m: 1 }}>Mid</Typography>
                            <Typography display="inline" sx={{ backgroundColor: 'pink', m: 1 }}>End</Typography>
                        </Box>
                    </Box>
                </>
            )}
        </Box>
    );
};

export default BinarySearchVisualization;
