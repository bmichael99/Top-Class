import ratemyprofessor
from flask import Flask, request, jsonify
from urllib.parse import unquote

def ratemyProfessorAPI(school_name,prof_name):
    #school_name = unquote(school_name)
    #prof_name = unquote(prof_name)
    print(str(school_name))
    print(str(prof_name))
    getProfessor = ratemyprofessor.get_professor_by_school_and_name(
        ratemyprofessor.get_school_by_name("California State University San Marcos"), "Lanier Castillo")
    if getProfessor is not None:
        return(getProfessor)

def testAPI():
    getProfessor = ratemyprofessor.get_professor_by_school_and_name(
        ratemyprofessor.get_school_by_name("California State University San Marcos"), "Lanier Castillo")
    if getProfessor is not None:
        print("%s works in the %s Department of %s." % (getProfessor.name, getProfessor.department, getProfessor.school.name))
        print("Rating: %s / 5.0" % getProfessor.rating)
        print("Difficulty: %s / 5.0" % getProfessor.difficulty)
        print("Total Ratings: %s" % getProfessor.num_ratings)
        if getProfessor.would_take_again is not None:
            print(("Would Take Again: %s" % round(getProfessor.would_take_again, 1)) + '%')
        else:
            print("Would Take Again: N/A")
        print(dir(getProfessor))
        print(getProfessor)



app = Flask(__name__)



@app.route('/get_Professor', methods=['GET'])
def get_Professor():
    professor_name = request.args.get('professor')
    school_name = request.args.get('school')
    getProfessor = ratemyProfessorAPI(professor_name,school_name)
    print("test" + getProfessor.name)
    professor = [{'name': getProfessor.name, 'department': getProfessor.department, 'school': getProfessor.school.name, 
              'rating' : getProfessor.rating, 'difficulty' : getProfessor.difficulty, 'totalratings' : getProfessor.num_ratings, 'would_take_again' : round(getProfessor.would_take_again, 1)}]
    return jsonify({'professor': professor})

if __name__ == '__main__':
    app.run(debug=True)
        