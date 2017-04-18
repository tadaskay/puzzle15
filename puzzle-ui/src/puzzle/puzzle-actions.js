import axios from 'axios';

export const types = {
  CREATE_NEW_PUZZLE: 'CREATE_NEW_PUZZLE'
};

export const actions = {
  createNewPuzzle: (uri) => ({
    type: types.CREATE_NEW_PUZZLE,
    payload: {
      promise: axios.post(uri)
    }
  })
};
