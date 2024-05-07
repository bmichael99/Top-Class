import React from 'react';
import './App.css';
import { Navbar } from './layouts/NavbarAndFooter/Navbar';
import { Footer } from './layouts/NavbarAndFooter/Footer';
import { HomePage } from './layouts/HomePage/HomePage';
import { ClassSelect } from './layouts/SelectClasses/ClassSelect';
import { Redirect, Route, Switch, useHistory } from 'react-router-dom';
import { oktaConfig } from './lib/oktaConfig';
import { OktaAuth, toRelativeUrl } from '@okta/okta-auth-js';
import { Security, LoginCallback } from '@okta/okta-react';
import {Schedule} from './layouts/Schedule/Schedule';
import LoginWidget from './Auth/LoginWidget';


const oktaAuth = new OktaAuth(oktaConfig);

export const App = () => {

  const customAuthHandler = () => {
    history.push('/login');
  }

  const history = useHistory();

  const restoreOrigninalUri = async (_oktaAuth: any, originalUri: any) => {
    history.replace(toRelativeUrl(originalUri || '/', window.location.origin));
  };

  return (
    <div className='d-flex flex-column min-vh-100'>
      <Security oktaAuth={oktaAuth} restoreOriginalUri={restoreOrigninalUri} onAuthRequired={customAuthHandler}>
      <Navbar />
      <div className='flex-grow-1'>
        <Switch>
          <Route path='/' exact>
            <Redirect to='/home' />
          </Route>
          <Route path='/search'>
            <ClassSelect />
          </Route>
          <Route path='/schedule'>
            <Schedule />
          </Route>
          
          <Route path='/home'>
            <HomePage />
          </Route>
          <Route path='/login' render={
            () => <LoginWidget config={oktaConfig} />
          }
          />
          <Route path='/login/callback' component={LoginCallback}/>
        </Switch>
      </div>
      <Footer />
      </Security>
    </div>
  );
}