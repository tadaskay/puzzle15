import axios from 'axios';

export const types = {
  FETCH_API_INDEX: 'FETCH_API_INDEX'
};

export const actions = {
  fetchApiIndex: () => ({
    type: types.FETCH_API_INDEX,
    payload: {
      promise: axios.get('http://localhost:8080/api')
    }
  })
};
