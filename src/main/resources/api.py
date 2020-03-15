from flask import Flask

app = Flask(__name__)


@app.route("/")
def hello():
    print("inside python hello")
    return "Python Api is running..."


app.run()

