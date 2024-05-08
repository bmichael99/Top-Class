import { NavLink, Link } from 'react-router-dom'
import { useOktaAuth } from '@okta/okta-react';
import { SpinnerLoading } from '../Utils/SpinnerLoading';
import React, { useState } from 'react';
import { useEffect, useRef } from 'react';
import MotionCard from './MotionCard';
import {selectedProfessors} from "./MotionCard"



export const Schedule = () => {
    const { authState } = useOktaAuth();
  const [selectedClasses, setSelectedClasses] = useState<{ classID: string; professor_name: string; classTitle: string;}[]>([]);
  const [filteredClasses, setFilteredClasses] = useState<{ classID: string; professor_name: string; classTitle: string; difficulty :string; rating :string; totalratings :string; would_take_again :string; topTags :Array<string> }[]>([]);
  const [openStates, setOpenStates] = useState<{ [title: string]: boolean }>({});
  const groupedClasses = groupByTitle(filteredClasses);
    


    
  const handleClassSelection = (event: React.ChangeEvent<HTMLInputElement>) => {
    const { checked, value } = event.target;
    if (checked) {  
        const classObj = filteredClasses.find(classObj => classObj.classID === value);
        if (classObj) {
            setSelectedClasses(prevSelected => [...prevSelected, classObj]);
        }
    } else {
        setSelectedClasses(prevSelected =>
            prevSelected.filter(selected => selected.classID !== value)
        );
    }
};



    const ClassCard: React.FC<{ classObj: { classID: string; professor_name: string; classTitle: string; difficulty :string; rating :string; totalratings :string; would_take_again :string; topTags :Array<string>} }> = ({ classObj }) => {
      const isSelected = selectedClasses.some(selected => selected.classID === classObj.classID);
      return (
          <div style={{ height: '250px', width: '400px', border: '1px solid #ccc', borderRadius: '5px', padding: '10px', marginBottom: '10px', textAlign: 'center', listStyleType: 'none'  }}>
              <input
                  value={classObj.classID}
                  type="checkbox"
                  checked={selectedClasses.some(selected => selected.classID === classObj.classID)}
                  onChange={handleClassSelection}
              />
              <strong>Title:</strong> {classObj.classID}<br />
              <strong>Professor:</strong> {classObj.professor_name}<br />
              <strong>Description:</strong> {classObj.classTitle}<br />
              <strong>Rating:</strong> {classObj.rating} <span>/ 5</span><br />
              <strong>Would Take Again:</strong> {classObj.would_take_again} <span>%</span> <br />
              <strong>Difficulty:</strong> {classObj.difficulty} <span>/ 5</span><br />
              <strong>Total Ratings:</strong> {classObj.totalratings} <br />
              <strong>Tags:</strong> {classObj.topTags[0]}, {classObj.topTags[1]}, {classObj.topTags[2]}, {classObj.topTags[3]}, {classObj.topTags[4]} <br />
          </div>
      );
  };

  function groupByTitle(classes: { classID: string; professor_name: string; classTitle: string; difficulty :string; rating :string; totalratings :string; would_take_again :string; topTags :Array<string> }[]) {
    return classes.reduce((acc, classObj) => {
        const title = classObj.classID;
        if (!acc[title]) {
            acc[title] = [];
        }
        acc[title].push(classObj);
        return acc;
    }, {} as Record<string, { classID: string; professor_name: string; classTitle: string; difficulty :string; rating :string; totalratings :string; would_take_again :string; topTags :Array<string> }[]>);
}

  
    var profNames: any[];
    const onClick = () => {
        
      // Create an array of classes from unchecked checkboxes
    const uncheckedClasses = Array.from(document.querySelectorAll('input[type="checkbox"]'))
    .filter((checkbox: Element) => !(checkbox as HTMLInputElement).checked)
    .map((checkbox: Element) => (checkbox as HTMLInputElement).value);

// Log the array of unchecked classes to the console
      console.log('Unchecked classes:', uncheckedClasses);
      //console.log("selected Professors:" + selectedProfessors[0].classID);
      

        if(authState){
            fetch(`http://localhost:8080/user/getUser`, {
            method: 'GET',
            headers: {
                Authorization: `Bearer ${authState.accessToken?.accessToken}`,
                'Content-Type': 'application/json',
            }
            })
            .then(res => res.json())
            .then(result => {
                
                profNames = result.data;
                const names = profNames.map(professor => professor.professor_name);
                const classIDs = profNames.map(professor => professor.classID);
                //console.log("names:" + names);
                //console.log("classIDs:" + classIDs);
                //console.log("222:", result);

                
                    
                        fetch(`http://localhost:8080/rating/getRatings?professor_names=${names.join(',')}`)
                            .then(res => res.json())
                            .then(result => {
                                
                                console.log("222:", result);

                                for(var i = 0; i < result.length;i++){
                                    const foundProf = profNames.filter((prof: { professor_name: any; }) => prof.professor_name === result[i].name)
                                    if (foundProf.length > 0) {
                                        foundProf.forEach((foundProf) => {
                                            foundProf["difficulty"] = result[i].difficulty;
                                            foundProf["rating"] = result[i].rating;
                                            foundProf["totalratings"] = result[i].totalratings;
                                            foundProf["would_take_again"] = result[i].would_take_again;
                                            foundProf["topTags"] = result[i].topTags;
                                        });
                                    }
                                }
                            
                                setFilteredClasses(profNames); 
                                console.log(filteredClasses);
                            })
                            .catch(error => {
                                console.error("Error fetching ratings: " + error);
                            });
                    
                
            })
            .catch(error => {
                console.error("Error fetching ratings: " + error);
            });
        }
      
                
        
          
          
          
  };

  

  const toggleList = (title: string) => {
    setOpenStates(prevOpenStates => ({
        ...prevOpenStates,
        [title]: !prevOpenStates[title]
    }));
};

    return (

        <div>
          
            <div style={{ textAlign: 'center' }}>
              <button style={{ marginTop: '50px'}} className='btn main-color btn-lg text-white' onClick={onClick}>
                Build Schedule
                </button>
            </div>
            <div style={{ marginTop: '20px', textAlign: 'center' }}>
                {Object.entries(groupedClasses).length > 0 ? (
                    Object.entries(groupedClasses).map(([title, classes]) => (
                      <div key={title}>
                          <h4 onClick={() => toggleList(title)} style={{ cursor: 'pointer' }}>{title}</h4>
                          {openStates[title] && (
                              <ul style={{ listStyleType: 'none', textAlign: 'center' }}>
                                  {classes.map((classObj, index) => (
                                      <li key={index} style={{ display: 'inline-block', margin: '0 10px' }}>
                                          <MotionCard classObj={classObj} />
                                      </li>
                                  ))}
                              </ul>
                          )}
                          
                      </div>

                      
                  ))
                  
                ) : (
                    <p></p>
                )}
                
            </div>
            
        
        </div>
        
    
      );
}