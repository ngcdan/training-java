import React from 'react';
import { Switch, Route } from 'react-router-dom';

function App() {
  return (
    <div className='container-fluid'>
      <Switch>
        <Route exact path='/' render={() => <h1>Hello World!</h1>} />
      </Switch>
    </div>
  );
}

export default App;