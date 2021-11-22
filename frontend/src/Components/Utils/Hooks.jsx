import { useState } from "react";
import axios from "axios";

export function useModal() {
    const [isOpen, setOpen] = useState(false);

    function open() {
        setOpen(true);
    }

    function close() {
        setOpen(false);
    }

    return [isOpen, open, close];
}

export function useApi() {
    const api = axios.create({
        baseURL: "http://localhost:8080/api/",
        timeout: 15000
    });

    api.interceptors.request.use(config => {
        return config;
    });
    
    api.interceptors.response.use(response => response, error => {
        console.warn("Axios error: " + error);
    });

    return api;
}

export function useDateParser() {
    return (date) => {
        const m = ["janvier", "février", "mars", "avril", "mai", "juin", "juillet", "août", "septembre", "octobre", "novembre", "décembre"];
        const d = new Date(date);
        return d.getDate() + " " + m[d.getMonth()] + " " + d.getFullYear();
    }
}

export function useTimeParserFromDate() {
    return (date) => {
        return new Date(date).toLocaleTimeString([], { hour: '2-digit', minute: '2-digit' })
    }
}