import React, { useState, useEffect } from 'react';
import { Slider, Button, Box } from '@mui/material';
import { useNavigate } from 'react-router-dom';

const QuickSortVisualization = () => {
    const navigate = useNavigate();
    const [array, setArray] = useState([]);
    const [arraySize, setArraySize] = useState(10);
    const [isSorting, setIsSorting] = useState(false);
    const [isSorted, setIsSorted] = useState(false);
    const [sortedIndices, setSortedIndices] = useState(new Set());

    useEffect(() => {
        generateNewArray(arraySize);
    }, [arraySize]);

    const generateNewArray = (size) => {
        const newArray = Array.from({ length: size }, () => Math.floor(Math.random() * 100) + 1);
        setArray(newArray);
        setIsSorted(false);
        setSortedIndices(new Set());
    };

    const saveVisualizationToHistory = () => {
        const history = JSON.parse(localStorage.getItem('visualizationHistory')) || [];
        history.push({
            type: 'algorithm',
            simpleName: 'quicksort',
            fullName: 'Быстрая сортировка',
            timestamp: new Date().toISOString(),
            params: Array.from(new Map([
                ["Размер", arraySize]
            ]).entries()),
            link: 'http://localhost:3000/visualize/algorithm/quicksort'
        });
        localStorage.setItem('visualizationHistory', JSON.stringify(history));
    };

    const quickSort = async (arr, start, end) => {
        if (start >= end) {
            setSortedIndices(prev => new Set([...prev, start]));
            return;
        }
        let index = await partition(arr, start, end);
        await quickSort(arr, start, index - 1);
        await quickSort(arr, index + 1, end);
    };

    const partition = async (arr, start, end) => {
        const pivotValue = arr[end];
        let pivotIndex = start;
        for (let i = start; i < end; i++) {
            if (arr[i] < pivotValue) {
                [arr[i], arr[pivotIndex]] = [arr[pivotIndex], arr[i]];
                setArray([...arr]);
                await sleep(150);
                pivotIndex++;
            }
        }
        [arr[pivotIndex], arr[end]] = [arr[end], arr[pivotIndex]];
        setArray([...arr]);
        setSortedIndices(prev => new Set([...prev, pivotIndex]));
        await sleep(150);
        return pivotIndex;
    };

    const handleSort = async () => {
        saveVisualizationToHistory();
        setIsSorting(true);
        await quickSort([...array], 0, array.length - 1);
        setIsSorting(false);
        setIsSorted(true);
    };

    const handleReset = () => {
        setIsSorting(false);
        setIsSorted(false);
        generateNewArray(arraySize);
    };

    const sleep = (ms) => new Promise(resolve => setTimeout(resolve, ms));

    return (
        <Box sx={{ width: '80%', margin: 'auto', overflow: 'hidden' }}>
            <Box sx={{ display: 'flex', flexDirection: 'column', alignItems: 'center', gap: 2 }}>
                <Box sx={{ height: '350px', width: '100%', overflow: 'auto', display: 'flex', justifyContent: 'center', alignItems: 'flex-end', paddingTop: '30px' }}>
                    {array.map((value, idx) => (
                        <div key={idx} style={{
                            height: `${value}%`,
                            width: '20px',
                            backgroundColor: sortedIndices.has(idx) ? 'green' : 'lightblue',
                            margin: '0 2px',
                            display: 'flex',
                            alignItems: 'flex-end',
                            justifyContent: 'center',
                            fontSize: '12px',
                            color: 'black',
                            transition: 'height 0.3s',
                            position: 'relative',
                        }}>
                            <span style={{
                                position: 'absolute',
                                top: '-20px',
                                width: '100%',
                                textAlign: 'center'
                            }}>
                                {value}
                            </span>
                        </div>
                    ))}
                </Box>
                <Box sx={{ padding: '0 16px', width: '90%' }}>
                    <Slider
                        value={arraySize}
                        onChange={(e, newValue) => {
                            if (!isSorting && !isSorted) {
                                setArraySize(newValue);
                            }
                        }}
                        aria-labelledby="array-size"
                        valueLabelDisplay="auto"
                        step={1}
                        marks
                        min={5}
                        max={30}
                        disabled={isSorting || isSorted}
                    />
                </Box>
                <Box>
                    <Button variant="contained" onClick={handleSort} disabled={isSorting || isSorted} sx={{ mt: 2 }} style={{ margin: '10px' }}>Запуск визуализации</Button>
                    <Button variant="contained" onClick={handleReset} disabled={isSorting} sx={{ mt: 2 }} style={{ margin: '10px' }}>Сброс</Button>
                    <Button onClick={() => navigate(-1)} variant="contained" color="secondary" style={{ margin: '10px' }}>Назад</Button>
                </Box>
            </Box>
        </Box>
    );
};

export default QuickSortVisualization;
