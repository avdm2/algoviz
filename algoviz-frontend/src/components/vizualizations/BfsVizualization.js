import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import Graph from 'react-graph-vis';
import { Button, Box, Typography } from '@mui/material';

const graph = {
    nodes: [
        { id: 1, label: '1', title: "Node 1", color: 'LightBlue' },
        { id: 2, label: '2', title: "Node 2", color: 'LightBlue' },
        { id: 3, label: '3', title: "Node 3", color: 'LightBlue' },
        { id: 4, label: '4', title: "Node 4", color: 'LightBlue' },
        { id: 5, label: '5', title: "Node 5", color: 'LightBlue' },
        { id: 6, label: '6', title: "Node 6", color: 'LightBlue' },
        { id: 7, label: '7', title: "Node 7", color: 'LightBlue' },
        { id: 8, label: '8', title: "Node 8", color: 'LightBlue' },
        { id: 9, label: '9', title: "Node 9", color: 'LightBlue' },
        { id: 10, label: '10', title: "Node 10", color: 'LightBlue' },
        { id: 11, label: '11', title: "Node 11", color: 'LightBlue' },
        { id: 12, label: '12', title: "Node 12", color: 'LightBlue' },
        { id: 13, label: '13', title: "Node 13", color: 'LightBlue' },
        { id: 14, label: '14', title: "Node 14", color: 'LightBlue' },
        { id: 15, label: '15', title: "Node 15", color: 'LightBlue' }
    ],
    edges: [
        { id: 1, from: 1, to: 2 },
        { id: 2, from: 1, to: 3 },
        { id: 3, from: 2, to: 5 },
        { id: 4, from: 2, to: 6 },
        { id: 5, from: 6, to: 7 },
        { id: 6, from: 7, to: 8 },
        { id: 7, from: 8, to: 3 },
        { id: 8, from: 9, to: 4 },
        { id: 9, from: 2, to: 9 },
        { id: 10, from: 9, to: 10 },
        { id: 11, from: 10, to: 11 },
        { id: 12, from: 11, to: 12 },
        { id: 13, from: 12, to: 6 },
        { id: 14, from: 1, to: 4 },
        { id: 15, from: 4, to: 5 },
        { id: 16, from: 5, to: 12 },
        { id: 17, from: 12, to: 3 },
        { id: 18, from: 3, to: 13 },
        { id: 19, from: 13, to: 14 },
        { id: 20, from: 14, to: 15 },
        { id: 21, from: 15, to: 1 },
        { id: 22, from: 15, to: 6 },
        { id: 23, from: 14, to: 9 },
        { id: 24, from: 13, to: 4 },
        { id: 25, from: 10, to: 14 },
        { id: 26, from: 10, to: 7 }
    ]
};

const options = {
    layout: {
        hierarchical: false
    },
    edges: {
        color: "#000000"
    },
    height: "500px"
};

const BFSVisualization = () => {
    const navigate = useNavigate();
    const [log, setLog] = useState([]);
    const [currentStep, setCurrentStep] = useState(0);
    const [, setVisited] = useState(new Set());
    const [bfsOrder, setBfsOrder] = useState([]);
    const [, setGraphState] = useState(graph);

    const saveVisualizationToHistory = () => {
        const history = JSON.parse(localStorage.getItem('visualizationHistory')) || [];
        history.push({
            type: 'algorithm',
            simpleName: 'bfs',
            fullName: 'Поиск в ширину',
            timestamp: new Date().toISOString(),
            link: 'https://algoviz-avdm2.netlify.app/visualize/algorithm/bfs'
        });
        localStorage.setItem('visualizationHistory', JSON.stringify(history));
    };

    const bfs = (startNodeId) => {
        const visited = new Set();
        const steps = [];
        const queue = [{ nodeId: startNodeId, parentNodeId: null }];

        while (queue.length > 0) {
            const { nodeId, parentNodeId } = queue.shift();
            if (visited.has(nodeId)) continue;

            visited.add(nodeId);
            steps.push({ nodeId, parentNodeId });

            const neighbors = graph.edges
                .filter(edge => edge.from === nodeId && !visited.has(edge.to))
                .map(edge => ({ nodeId: edge.to, parentNodeId: nodeId }));

            for (let i = neighbors.length - 1; i >= 0; i--) {
                queue.push(neighbors[i]);
            }
        }

        return steps;
    };

    const handleStartBFS = () => {
        saveVisualizationToHistory();
        setLog([]);
        setVisited(new Set());
        setCurrentStep(0);
        const steps = bfs(1);
        setBfsOrder(steps);
        resetGraphColors();
    };

    const resetGraphColors = () => {
        setGraphState({
            nodes: graph.nodes.map(node => ({ ...node, color: 'LightBlue' })),
            edges: graph.edges.map(edge => ({ ...edge, color: '#000000' }))
        });
    };

    const handleNextStep = () => {
        const step = bfsOrder[currentStep];
        const newLog = `Посещена вершина ${step.nodeId} из ${step.parentNodeId || 'старта'}`;
        setLog(prev => [...prev, newLog]);
        setCurrentStep(currentStep + 1);
    };

    const graphWithVisited = {
        ...graph,
        edges: graph.edges.map(edge => ({
            ...edge,
            color: bfsOrder.slice(0, currentStep).find(step => step.nodeId === edge.to && step.parentNodeId === edge.from) ? 'red' : '#000000'
        })),
        nodes: graph.nodes.map(node => ({
            ...node,
            color: bfsOrder.slice(0, currentStep).find(step => step.nodeId === node.id) ? 'lightgreen' : node.color
        }))
    };

    return (
        <Box sx={{ display: 'flex' }}>
            <Graph graph={graphWithVisited} options={options} />
            <Box>
                <Button variant="contained" color="primary" onClick={handleStartBFS} sx={{ mt: 2 }} style={{ margin: '10px' }}>
                    Запустить визуализацию
                </Button>
                <Button variant="contained" color="primary" onClick={handleNextStep} disabled={currentStep >= bfsOrder.length} sx={{ mt: 2 }} style={{ margin: '10px' }}>
                    Следующий шаг
                </Button>
                <Button onClick={() => navigate(-1)} variant="contained" color="secondary" style={{ margin: '10px' }}>
                    Назад
                </Button>
                {(
                    <Box>
                        {log.slice(0, currentStep).map((entry, idx) => (
                            <Typography key={idx}>{entry}</Typography>
                        ))}
                    </Box>
                )}
            </Box>
        </Box>
    );
};

export default BFSVisualization;