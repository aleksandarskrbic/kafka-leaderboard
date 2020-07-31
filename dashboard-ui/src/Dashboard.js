import React from 'react';
import Table from 'react-bootstrap/Table';
import Image from 'react-bootstrap/Image';
import './Dashboard.css';

export default function Dasboard({ state }) {

    return (
        <div>
            <Table striped bordered hover responsive variant='dark' size="md" className='colorBlack'>
                <thead>
                    <tr>
                        <th>Position</th>
                        <th>User</th>
                        <th>Points</th>
                    </tr>
                </thead>
                <tbody>
                    {state.map((row, index) => (
                        <tr key={row.username}>
                            <td>{index + 1}.</td>
                            { row.url && row.avatarUrl ? (
                                <td> 
                                    <a href={row.url}>
                                        <Image src={row.avatarUrl} className='imgHeight' roundedCircle />
                                        <h4 className="nameCenter">{row.username}</h4>
                                    </a>
                                </td>
                            ) : (
                                <td>
                                    <a href={row.url}>
                                        <Image src="https://avatars3.githubusercontent.com/u/12502538?v=4" className='imgHeight' roundedCircle/>
                                        <h4 className="nameCenter">{row.username}</h4>
                                    </a>
                                </td>
                            )}
                            <td>{row.points}</td>
                        </tr>
                    ))}
                </tbody>
            </Table>
        </div>
    );
}