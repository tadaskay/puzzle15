import React from 'react';
import PropTypes from 'prop-types';
import {bindActionCreators} from 'redux';
import {connect} from 'react-redux';
import {actions} from './puzzle-actions';

const propTypes = {
  links: PropTypes.arrayOf(PropTypes.object).isRequired,
  actions: PropTypes.objectOf(PropTypes.func.isRequired).isRequired
};

class Keys extends React.Component {

  move(direction) {
    let matchingDirection = link => link.rel === direction;
    this.props.actions.move(this.props.links.find(matchingDirection).href);
  }

  render() {
    return (
      <div id="keys">
        {this.props.links.map(link => {
          let direction = link.rel.replace('move-', '');
          return (
            <div className="key" key={link.rel} onClick={() => this.move(link.rel)}>
              <i className={`fa fa-2x fa-chevron-${direction}`} aria-hidden="true"/>
            </div>
          );
        })}
      </div>
    );
  }
}

Keys.propTypes = propTypes;

const mapStateToProps = (state) => ({
  links: state.puzzle.links
});
const mapDispatchToProps = (dispatch) => ({
  actions: bindActionCreators(actions, dispatch)
});

export default connect(mapStateToProps, mapDispatchToProps)(Keys);
