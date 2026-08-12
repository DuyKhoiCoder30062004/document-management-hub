// src/api/documentApi.js
import axios from "axios";

const API_BASE_URL = "http://localhost:8080/api/v1/document-manager"; 
// Adjust port if your Spring Boot backend runs on a different one

export const getDocuments = async () => {
  const response = await axios.get(API_BASE_URL);
  return response.data;
};

// Get document by ID
export const getDocumentById = async (id) => {
  const response = await axios.get(`${API_BASE_URL}/${id}`);
  return response.data;
};

export const addDocument = async (doc) => {
  const response = await axios.post(API_BASE_URL, doc);
  return response.data;
};

// Update document
export const updateDocument = async (id, doc) => {
  const response = await axios.put(`${API_BASE_URL}/${id}`, doc);
  return response.data;
};

// Delete document
export const deleteDocument = async (id) => {
  const response = await axios.delete(`${API_BASE_URL}/${id}`);
  return response.data;
};