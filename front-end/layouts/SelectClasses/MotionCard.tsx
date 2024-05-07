import React from 'react';
import { motion } from 'framer-motion';
import { useState } from 'react';

export var selectedProfessors: any[]
selectedProfessors = [];

interface MotionCardProps {
  classObj: {
    classID: string;
    name: string;
    classTitle: string;
    difficulty: string;
    rating: string;
    totalratings: string;
    would_take_again: string;
    topTags: Array<String>
  };
}

const MotionCard: React.FC<MotionCardProps> = ({ classObj }) => {
  const [isClicked, setIsClicked] = useState(false);
  
  

  const handleClick = () => {
    setIsClicked(!isClicked);
    if(!isClicked){
      selectedProfessors.push(classObj);
      console.log(selectedProfessors);
    }else{
      const index = selectedProfessors.findIndex(prof => prof.classID === classObj.classID);
      if (index > -1) {
        selectedProfessors.splice(index, 1);
      }
    }
    
  };

  const getRatingColor = (rating: string) => {
    const numericRating = parseFloat(rating);
    if (numericRating >= 4.0) {
      return '#C8E6C9'; // Green
    } else if (numericRating >= 3.0) {
      return '#FFF59D'; // Yellow
    } else if (numericRating >= 0 && numericRating < 3.0) {
      return '#FFCCBC'; // Light red
    } else {
      return 'transparent';
    }
  };

  return (
    <motion.div
      className='motion-card-container'
      onClick={handleClick}
    >
    <motion.div
      className="motion-card"
      initial={{ opacity: 0, y: 50 }}
      animate={{ opacity: 1, y: 0 }}
      exit={{ opacity: 0, y: 50}}
      transition={{ duration: 0.5 }}
      style={{
        position: "relative",
        alignItems: "center",
        display: "flex",
        width: isClicked ? '600px' : '600px',
        height: isClicked ? '100px' : '100px',
        backgroundColor:'0b94e3',
        borderRadius: '1rem',
        
        border: isClicked ? '4px solid red' : '1px solid #ccc', // Change border here when clicked
        }}
        // onClick={handleClick}
    >
      <div style={{ 
        position: "relative",
        alignItems: "center",
        display: "flex",
        height: '100px',
        width: '600px',
        borderRadius: '1rem', 
        padding: '10px', 
        marginBottom: '10px', 
        
        
        
        
        
        }}>
     
     
          
            <div
          style={{
            flex: 0.16,
            backgroundColor: getRatingColor(classObj.rating), // Background color of the box
            padding: "15px 5px", // Padding of the box
            marginRight: "10px", // Space between the box and text
            fontSize: "20px", // Increase font size
          }}
        >
          <strong>{classObj.rating}/5</strong>
        </div>
        <span style={{ flex: 0.5 }}>
            <strong
                style={{ 
                    position: "absolute", // Set the position to absolute
                    left: "95px", // Specify the distance from the right edge
                    marginTop: "-34px" }}
            >
                {classObj.name}
            </strong>
        </span>
          
        <span style={{  flex: 0.5, 
                        position: "relative", // Set the position to absolute
                        right: "218px", // Specify the distance from the right edge
                        marginTop: "75px" }}  >
                        <strong>{classObj.would_take_again}% </strong>
        </span>
        <span style={{  flex: 0.5, 
                        position: "relative", // Set the position to absolute
                        left: "-299px", // Specify the distance from the right edge
                        marginTop: "75px" }}  >
                        would take again      
        </span>

        <span style={{  flex: 0.5, 
                        position: "absolute", // Set the position to absolute
                        left: "330px", // Specify the distance from the right edge
                        marginTop: "75px" }}  >
                        <strong>{classObj.difficulty}% </strong> 
        </span>
        <span style={{  flex: 0.5, 
                        position: "absolute", // Set the position to absolute
                        left: "375px", // Specify the distance from the right edge
                        marginTop: "75px" }}  >
                        level of difficulty            
        </span>
      </div>
      <span style={{  flex: 0.1, 
                        position: "absolute", // Set the position to absolute
                        left: "90px", // Specify the distance from the right edge
                        top:"40px",
                        fontSize: "13px"
                        }}  >
                        {classObj.topTags && classObj.topTags.length > 0 ?
            classObj.topTags.map((tag, index) => (
              <span 
              key={index} 
              style={{ 
                       display: 'inline-block',
                       height: 'auto',
                       width: 'auto',  
                       position: "relative",
                       background: "#F1F1F1",
                       borderRadius: '1rem', 
                       padding: '5px', 
                       marginBottom: '10px',
                       fontSize: "10px"
            
            }} 
              
              
              
              >{tag}{index !== classObj.topTags.length - 1 ? '' : ''}</span>
            )) : "No tags"}
          
        </span>
    </motion.div>
    </motion.div>
  );
};

export default MotionCard;
