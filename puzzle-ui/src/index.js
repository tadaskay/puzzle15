/* eslint-disable import/default */

import React from 'react';
import {render} from 'react-dom';
import {AppContainer} from 'react-hot-loader';
import Root from './app/Root';

import configureStore from './configure-store';
import './styles/styles.scss';
require('./favicon.ico');

const store = configureStore();

render(
  <AppContainer>
    <Root store={store}/>
  </AppContainer>,
  document.getElementById('app')
);

if (module.hot) {
  module.hot.accept('./app/Root', () => {
    const NewRoot = require('./app/Root').default;
    render(
      <AppContainer>
        <NewRoot store={store}/>
      </AppContainer>,
      document.getElementById('app')
    );
  });
}
