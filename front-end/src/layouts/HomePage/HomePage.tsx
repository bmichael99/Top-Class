import { ExploreTopClasses } from './components/ExploreTopClasses';
import { PageServices } from './components/PageServices';
import { useEffect, useRef } from 'react';

function Test(){
    useEffect(()=>{
        fetch("http://localhost:8080/professor/getAll")
        .then(res=>res.json())
        .then((result)=>{
          console.log(result);
        }
      )
      },[])
}

export const HomePage = () => {
    Test();
    return (
        <>
        <ExploreTopClasses/>
        <PageServices/>
        </>
    );
}
