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
        { id: 9, label: '9', title: "Node 9", color: 'LightBlue' }
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
        { id: 9, from: 2, to: 9 }
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

const DFSVisualization = () => {
    const navigate = useNavigate();
    const [log, setLog] = useState([]);
    const [currentStep, setCurrentStep] = useState(0);
    const [visited, setVisited] = useState({});
    const [dfsOrder, setDfsOrder] = useState([]);
    const [graphState, setGraphState] = useState(graph);

    const dfs = (startNodeId) => {
        const visited = new Set();
        const steps = [];
        const stack = [{ nodeId: startNodeId, parentNodeId: null }];

        while (stack.length > 0) {
            const { nodeId, parentNodeId } = stack.pop();
            if (visited.has(nodeId)) continue;

            visited.add(nodeId);
            steps.push({ nodeId, parentNodeId });

            const neighbors = graph.edges
                .filter(edge => edge.from === nodeId && !visited.has(edge.to))
                .map(edge => ({ nodeId: edge.to, parentNodeId: nodeId }));

            for (let i = neighbors.length - 1; i >= 0; i--) {
                stack.push(neighbors[i]);
            }
        }

        return steps;
    };

    const handleStartDFS = () => {
        setLog([]);
        setVisited(new Set());
        setCurrentStep(0);
        const steps = dfs(1);
        setDfsOrder(steps);
        resetGraphColors();
    };

    const resetGraphColors = () => {
        setGraphState({
            nodes: graph.nodes.map(node => ({ ...node, color: 'LightBlue' })),
            edges: graph.edges.map(edge => ({ ...edge, color: '#000000' }))
        });
    };

    const handleNextStep = () => {
        const step = dfsOrder[currentStep];
        const newLog = `Посещена вершина ${step.nodeId}`;
        setLog(prev => [...prev, newLog]);
        setCurrentStep(currentStep + 1);
    };

    const graphWithVisited = {
        ...graph,
        edges: graph.edges.map(edge => ({
            ...edge,
            color: dfsOrder.slice(0, currentStep).find(step => step.nodeId === edge.to && step.parentNodeId === edge.from) ? 'red' : '#000000'
        })),
        nodes: graph.nodes.map(node => ({
            ...node,
            color: dfsOrder.slice(0, currentStep).find(step => step.nodeId === node.id) ? 'red' : node.color
        }))
    };

    return (
        <Box sx={{ display: 'flex' }}>
            <Graph graph={graphWithVisited} options={options} />
            <Box>
                <Button variant="contained" color="primary" onClick={handleStartDFS} sx={{ mt: 2 }} style={{ margin: '10px' }}>
                    Запустить визуализацию
                </Button>
                <Button variant="contained" color="primary" onClick={handleNextStep} disabled={currentStep >= dfsOrder.length} sx={{ mt: 2 }} style={{ margin: '10px' }}>
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

export default DFSVisualization;