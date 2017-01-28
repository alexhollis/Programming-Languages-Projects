var Drone = require('drone'),
    blocks = require('blocks');

var mycarpet = function( size )
{
	var i;
	if(typeof size == 'undefined') size = 1;
	
	this.chkpt('mycarpet');
	for(i=0; i< 1; i++)
	{
	
		this.box0(1,3,2,3)
			.fwd(3)
			.box0(1,3,2,3)
			.fwd(3)
			.box0(1,3,2,3)
			.right(3)
			.box0(1,3,2,3)
			.right(3)
			.box0(1,3,2,3)
			.back(3)
			.box0(1,3,2,3)
			.back(3)
			.box0(1,3,2,3)
			.left(3)
			.box0(1,3,2,3);
		
	
	}
	
	return this.move('mycarpet');


};



Drone.extend( 'mycarpet',mycarpet );



