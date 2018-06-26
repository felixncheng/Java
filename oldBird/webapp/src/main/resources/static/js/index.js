class Myfirst extends React.Component {
    constructor(props) {
        super(props);
        this.handlerClick = this.handlerClick.bind(this);
        this.state = {
            msg: ''
        };
    }
    handlerClick() {
        $.ajax({
            url: "/abc",
            data: {
                name: 'cheng'
            },
            success: function( result ) {
                this.setState({msg: result});
            }.bind(this)
        });
    }
    render() {
        return (
            <div>
                <label>
                    Return Message<input type="text" value={this.state.msg}/>;
                </label>
                <br/>
                <button name="hello" onClick={this.handlerClick}>click</button>

            </div>
        );
    }
}
ReactDOM.render(
    <Myfirst />,
    document.getElementById('root')
);
