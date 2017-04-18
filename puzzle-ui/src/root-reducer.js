import {combineReducers} from 'redux';
import apiIndex from './app/api-index-reducer';
import puzzle from './puzzle/puzzle-reducer';

const rootReducer = combineReducers({
  apiIndex,
  puzzle
});

export default rootReducer;
