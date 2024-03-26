import { NavLink, Link } from 'react-router-dom'
import { useOktaAuth } from '@okta/okta-react';
import { SpinnerLoading } from '../Utils/SpinnerLoading';
import React, { useState } from 'react';
import { useEffect, useRef } from 'react';

export const ClassSelect = () => {
    const [selectedClasses, setSelectedClasses] = useState<string[]>([]);
    const handleClassSelection = (event: React.ChangeEvent<HTMLInputElement>) => {
        const { value, checked } = event.target;
        if (checked) {
            console.log(value);
          setSelectedClasses((prevSelected) => [...prevSelected, value]);
        } else {
          setSelectedClasses((prevSelected) =>
            prevSelected.filter((selected) => selected !== value)
          );
          
        }
      };

      const onClick = () => {
        for(var i = 0;i<selectedClasses.length;i++){
            console.log(selectedClasses[i]);
        }

        
            fetch("http://localhost:8080/professor/getAll")
            .then(res=>res.json())
            .then((result)=>{
              console.log(result);
            }
          )
          
      }
    return (

        <div>
          
            <div style={{ textAlign: 'center' }}>
            <div>
              <h2>Please Select Courses Taken</h2> {/* Title */}
            </div>
            <div style={{ display: 'inline-block', textAlign: 'left' }}>
              <input value="CS310" type="checkbox" onChange={handleClassSelection}/>
              <span> CS 310: Social Issues and Professional Practices in Computing</span><br />
    
              <input value="CS311" type="checkbox" onChange={handleClassSelection}/>
              <span> CS 311: Data Structures and Algorithms</span><br />
    
              <input value="CS331" type="checkbox" onChange={handleClassSelection}/>
              <span> CS 331: Computer Architecture</span><br />
    
              <input value="CS351" type="checkbox" onChange={handleClassSelection}/>
              <span> CS 351: Programming Languages</span><br />
    
              <input value="CS370" type="checkbox" onChange={handleClassSelection}/>
              <span> CS 370: Introduction to Software Engineering</span><br />
    
              <input value="CS433" type="checkbox" onChange={handleClassSelection}/>
              <span> CS 433: Operating Systems</span><br />
    
              <input value="CS436" type="checkbox" onChange={handleClassSelection}/>
              <span> CS 436: Introduction to Networking</span><br />
    
              <input value="CS443" type="checkbox" onChange={handleClassSelection}/>
              <span> CS 443: Fundamentals of Database Systems</span><br />
    
              <input value="CS471" type="checkbox" onChange={handleClassSelection}/>
              <span> CS 471: Introduction to Artificial Intelligence</span><br />
    
              <input value="CS490" type="checkbox" onChange={handleClassSelection}/>
              <span> CS 490: Capstone Project</span><br />
              </div>
              <button onClick={onClick}>
                Submit Classes
                </button>
            </div>
            
        </div>
    
      );
}