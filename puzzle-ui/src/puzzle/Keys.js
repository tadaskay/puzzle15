import React from 'react';
import PropTypes from 'prop-types';
import {bindActionCreators} from 'redux';
import {connect} from 'react-redux';
import {actions} from './puzzle-actions';
import KeyHandler, {KEYDOWN} from 'react-key-handler';

const propTypes = {
  links: PropTypes.arrayOf(PropTypes.object).isRequired,
  actions: PropTypes.objectOf(PropTypes.func.isRequired).isRequired
};

class Keys extends React.Component {

  move(direction) {
    let link = this.props.links.find(link => link.direction === direction);
    if (link) {
      this.props.actions.move(link.href);
    }
  }

  render() {
    return (
      <div id="keys">
        <KeyHandler keyEventName={KEYDOWN} keyValue="ArrowUp" onKeyHandle={() => this.move('up')} />
        <KeyHandler keyEventName={KEYDOWN} keyValue="ArrowRight" onKeyHandle={() => this.move('right')} />
        <KeyHandler keyEventName={KEYDOWN} keyValue="ArrowDown" onKeyHandle={() => this.move('down')} />
        <KeyHandler keyEventName={KEYDOWN} keyValue="ArrowLeft" onKeyHandle={() => this.move('left')} />
        {this.props.links.map(link => {
          return (
            <div className="key" key={link.rel} onClick={() => this.move(link.rel)}>
              <i className={`fa fa-2x fa-chevron-${link.direction}`} aria-hidden="true"/>
            </div>
          );
        })}
      </div>
    );
  }
}

Keys.propTypes = propTypes;

const mapStateToProps = (state) => ({
  links: state.puzzle.links.map(link => {
    let direction = link.rel.replace('move-', '');
    return Object.assign({}, link, {
      direction: direction
    });
  })
});
const mapDispatchToProps = (dispatch) => ({
  actions: bindActionCreators(actions, dispatch)
});

export default connect(mapStateToProps, mapDispatchToProps)(Keys);
