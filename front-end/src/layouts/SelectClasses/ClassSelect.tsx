import { NavLink, Link } from 'react-router-dom'
import { useOktaAuth } from '@okta/okta-react';
import { SpinnerLoading } from '../Utils/SpinnerLoading';
import React, { useState } from 'react';
import { useEffect, useRef } from 'react';



export const ClassSelect = () => {
  const [selectedClasses, setSelectedClasses] = useState<{ classID: string; name: string; classTitle: string;}[]>([]);
  const [filteredClasses, setFilteredClasses] = useState<{ classID: string; name: string; classTitle: string; difficulty :string; rating :string; totalratings :string; would_take_again :string }[]>([]);
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



    const ClassCard: React.FC<{ classObj: { classID: string; name: string; classTitle: string; difficulty :string; rating :string; totalratings :string; would_take_again :string } }> = ({ classObj }) => {
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
              <strong>Professor:</strong> {classObj.name}<br />
              <strong>Description:</strong> {classObj.classTitle}<br />
              <strong>Rating:</strong> {classObj.rating} <text>/ 5</text><br />
              <strong>Would Take Again:</strong> {classObj.would_take_again} <text>%</text> <br />
              <strong>Difficulty:</strong> {classObj.difficulty} <text>/ 5</text><br />
              <strong>Total Ratings:</strong> {classObj.totalratings} <br />
          </div>
      );
  };

  function groupByTitle(classes: { classID: string; name: string; classTitle: string; difficulty :string; rating :string; totalratings :string; would_take_again :string }[]) {
    return classes.reduce((acc, classObj) => {
        const title = classObj.classID;
        if (!acc[title]) {
            acc[title] = [];
        }
        acc[title].push(classObj);
        return acc;
    }, {} as Record<string, { classID: string; name: string; classTitle: string; difficulty :string; rating :string; totalratings :string; would_take_again :string }[]>);
}

  

    const onClick = () => {
      // Create an array of classes from unchecked checkboxes
    const uncheckedClasses = Array.from(document.querySelectorAll('input[type="checkbox"]'))
    .filter((checkbox: Element) => !(checkbox as HTMLInputElement).checked)
    .map((checkbox: Element) => (checkbox as HTMLInputElement).value);

// Log the array of unchecked classes to the console
      console.log('Unchecked classes:', uncheckedClasses);
      
        var profRatings: any[];
      fetch(`http://localhost:8080/professor/getClassesNotTaken?notTaken=${uncheckedClasses.join(',')}`)
          .then(res => res.json())
          .then(result => {
            profRatings = result;
              //setFilteredClasses(result);
              console.log("Filtered classes from backend:", result);

              //create an array of professor names from array of professor objects
          const names = profRatings.map(professor => professor.name);
          
            fetch(`http://localhost:8080/rating/getRatings?professor_names=${names.join(',')}`)
                .then(res => res.json())
                .then(result => {
                    
                    console.log("222:", result);

                    for(var i = 0; i < result.length;i++){
                        const foundProf = profRatings.filter((prof: { name: any; }) => prof.name === result[i].name)
                        if (foundProf.length > 0) {
                            foundProf.forEach((foundProf) => {
                                foundProf["difficulty"] = result[i].difficulty;
                                foundProf["rating"] = result[i].rating;
                                foundProf["totalratings"] = result[i].totalratings;
                                foundProf["would_take_again"] = result[i].would_take_again;
                            });
                        }
                    }
                
                    setFilteredClasses(profRatings); 
                    console.log(filteredClasses);
                })
                .catch(error => {
                    console.error('333:', error);
                });
          })
          .catch(error => {
              console.error('Error fetching classes:', error);
          });
          
          
          
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
            <div>
              <h2>Please Select Courses Taken</h2> {/* Title */}
            </div>
            <div style={{ display: 'inline-block', textAlign: 'left' }}>
              <input value="CS 310" type="checkbox" onChange={handleClassSelection}/>
              <span> CS 310: Social Issues and Professional Practices in Computing</span><br />
    
              <input value="CS 311" type="checkbox" onChange={handleClassSelection}/>
              <span> CS 311: Data Structures and Algorithms</span><br />
    
              <input value="CS 331" type="checkbox" onChange={handleClassSelection}/>
              <span> CS 331: Computer Architecture</span><br />
    
              <input value="CS 351" type="checkbox" onChange={handleClassSelection}/>
              <span> CS 351: Programming Languages</span><br />
    
              <input value="CS 370" type="checkbox" onChange={handleClassSelection}/>
              <span> CS 370: Introduction to Software Engineering</span><br />
    
              <input value="CS 433" type="checkbox" onChange={handleClassSelection}/>
              <span> CS 433: Operating Systems</span><br />
    
              <input value="CS 436" type="checkbox" onChange={handleClassSelection}/>
              <span> CS 436: Introduction to Networking</span><br />
    
              <input value="CS 443" type="checkbox" onChange={handleClassSelection}/>
              <span> CS 443: Fundamentals of Database Systems</span><br />
    
              <input value="CS 471" type="checkbox" onChange={handleClassSelection}/>
              <span> CS 471: Introduction to Artificial Intelligence</span><br />
    
              <input value="CS 490" type="checkbox" onChange={handleClassSelection}/>
              <span> CS 490: Capstone Project</span><br />
              </div>
              <button onClick={onClick}>
                Submit Classes
                </button>
            </div>
            <div style={{ marginTop: '20px', textAlign: 'center' }}>
                <h3>Classes Not Taken:</h3>
                {Object.entries(groupedClasses).length > 0 ? (
                    Object.entries(groupedClasses).map(([title, classes]) => (
                      <div key={title}>
                          <h4 onClick={() => toggleList(title)} style={{ cursor: 'pointer' }}>{title}</h4>
                          {openStates[title] && (
                              <ul style={{ listStyleType: 'none', textAlign: 'center' }}>
                                  {classes.map((classObj, index) => (
                                      <li key={index} style={{ display: 'inline-block', margin: '0 10px' }}>
                                          <ClassCard classObj={classObj} />
                                      </li>
                                  ))}
                              </ul>
                          )}
                      </div>
                  ))
                ) : (
                    <p>No classes to display</p>
                )}
            </div>
        
        </div>
        
    
      );
}