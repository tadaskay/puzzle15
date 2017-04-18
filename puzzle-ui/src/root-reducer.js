import {combineReducers} from 'redux';
import apiIndex from './app/api-index-reducer';

const rootReducer = combineReducers({
  apiIndex
});

export default rootReducer;
