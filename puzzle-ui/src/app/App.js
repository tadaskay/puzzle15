import React from 'react';
import PropTypes from 'prop-types';
import {bindActionCreators} from 'redux';
import {connect} from 'react-redux';
import {actions} from './api-index-actions';

const propTypes = {
  children: PropTypes.node,
  actions: PropTypes.objectOf(PropTypes.func.isRequired).isRequired
};

class App extends React.Component {

  componentDidMount() {
    this.props.actions.fetchApiIndex();
  }

  render() {
    return (
      <div>
        <h1>15-puzzle</h1>
        {this.props.children}
      </div>
    );
  }
}

App.propTypes = propTypes;

const mapDispatchToProps = (dispatch) => ({
  actions: bindActionCreators(actions, dispatch)
});

export default connect(null, mapDispatchToProps)(App);
