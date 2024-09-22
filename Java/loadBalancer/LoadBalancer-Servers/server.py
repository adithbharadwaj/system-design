from flask import Flask

app = Flask(__name__)
global port

@app.route('/', methods=['GET', 'POST'])
def hello_world():
    return 'Hello World. Running on: ' + str(port)

@app.route('/hello', methods=['GET', 'POST'])
def hello():
    return 'Hello World. Running on: ' + str(port)

if __name__ == '__main__':
    port = int(input('Enter the port: '))
    app.run(port=port)