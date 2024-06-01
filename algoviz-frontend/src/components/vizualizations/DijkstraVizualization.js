import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import Graph from 'react-graph-vis';
import { Button, Box, Typography, TextField } from '@mui/material';

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

const DijkstraVizualization = () => {
    const navigate = useNavigate();
    const [log, setLog] = useState([]);
    const [currentStep, setCurrentStep] = useState(0);
    const [startNode, setStartNode] = useState('');
    const [endNode, setEndNode] = useState('');
    const [graphState, setGraphState] = useState(graph);
    const [dijkstraOrder, setDijkstraOrder] = useState([]);

    const saveVisualizationToHistory = () => {
        const history = JSON.parse(localStorage.getItem('visualizationHistory')) || [];
        history.push({
            type: 'algorithm',
            simpleName: 'dijkstra',
            fullName: 'Алгоритм Дейкстры',
            timestamp: new Date().toISOString(),
            params: Array.from(new Map([
                ["Старт", startNode],
                ["Конец", endNode]
            ]).entries()),
            link: 'https://algoviz-avdm2.netlify.app/visualize/algorithm/dijkstra'
        });
        localStorage.setItem('visualizationHistory', JSON.stringify(history));
    };

    const dijkstra = (graph, sourceId) => {
        const distances = {};
        const previous = {};
        const nodes = new Set(graph.nodes.map(node => node.id));

        graph.nodes.forEach(node => {
            distances[node.id] = Infinity;
            previous[node.id] = null;
        });

        distances[sourceId] = 0;

        while (nodes.size) {
            let minNode = null;
            nodes.forEach(node => {
                if (minNode === null || distances[node] < distances[minNode]) {
                    minNode = node;
                }
            });

            nodes.delete(minNode);
            if (distances[minNode] === Infinity) {
                break;
            }

            graph.edges.filter(edge => edge.from === minNode).forEach(edge => {
                const target = edge.to;
                const cost = 10;
                const costThroughMinNode = distances[minNode] + cost;
                if (costThroughMinNode < distances[target]) {
                    distances[target] = costThroughMinNode;
                    previous[target] = minNode;
                }
            });
        }

        return { distances, previous };
    };

    const buildSteps = (previous, targetId) => {
        let current = targetId;
        const steps = [];
        while (current !== null) {
            steps.unshift({ nodeId: current, parentNodeId: previous[current] });
            current = previous[current];
        }
        return steps;
    };

    const handleStartDijkstra = () => {
        if (!startNode || isNaN(startNode) || !endNode || isNaN(endNode)) {
            alert("Укажите начальную и конечную вершину.");
            return;
        }

        saveVisualizationToHistory();
        setLog([]);
        setCurrentStep(0);
        const results = dijkstra(graphState, parseInt(startNode));
        setDijkstraOrder(buildSteps(results.previous, parseInt(endNode)));
        resetGraphColors();
    };

    const resetGraphColors = () => {
        setGraphState({
            nodes: graph.nodes.map(node => ({ ...node, color: 'LightBlue' })),
            edges: graph.edges.map(edge => ({ ...edge, color: '#000000' }))
        });
    };

    const handleNextStep = () => {
        const step = dijkstraOrder[currentStep];
        const newLog = `Посещена вершина ${step.nodeId} из ${step.parentNodeId || 'старта'}`;
        setLog(prev => [...prev, newLog]);
        setCurrentStep(currentStep + 1);
    };

    const graphWithVisited = {
        ...graph,
        edges: graph.edges.map(edge => ({
            ...edge,
            color: dijkstraOrder.slice(0, currentStep).find(step => step.nodeId === edge.to && step.parentNodeId === edge.from) ? 'red' : '#000000'
        })),
        nodes: graph.nodes.map(node => ({
            ...node,
            color: dijkstraOrder.slice(0, currentStep).find(step => step.nodeId === node.id) ? 'lightgreen' : node.color
        }))
    };

    return (
        <Box sx={{ display: 'flex' }}>
            <Graph graph={graphWithVisited} options={options} />
            <Box>
                <TextField
                    label="Начальная вершина"
                    value={startNode}
                    onChange={(e) => setStartNode(e.target.value)}
                    sx={{ mt: 2 }}
                    style={{ margin: '10px' }}
                />
                <TextField
                    label="Конечная вершина"
                    value={endNode}
                    onChange={(e) => setEndNode(e.target.value)}
                    sx={{ mt: 2 }}
                    style={{ margin: '10px' }}
                />
                <Button variant="contained" onClick={handleStartDijkstra} style={{ margin: '10px' }}>
                    Запустить визуализацию
                </Button>
                <Button variant="contained" onClick={handleNextStep} disabled={currentStep >= dijkstraOrder.length}>
                    Следующий шаг
                </Button>
                <Button onClick={() => navigate(-1)} variant="contained" color="secondary" style={{ margin: '10px' }}>Назад</Button>
                <Box>
                    {log.map((entry, idx) => (
                        <Typography key={idx}>{entry}</Typography>
                    ))}
                </Box>
            </Box>
        </Box>
    );
};

export default DijkstraVizualization;