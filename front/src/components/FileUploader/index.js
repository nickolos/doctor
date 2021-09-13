import {useState} from 'react';
import axios from 'axios';
import {toast} from 'react-toastify';

import './style.css';

export const FileUploader = ({onSuccess}) => {
    const [file, setFiles] = useState(null);

    const onInputChange = (e) => {
        setFiles(e.target.files[0]);
    };

    const onSubmit = (e) => {
        e.preventDefault();

        const data = new FormData();
        data.append('file', file);
        axios.post('//localhost:9000/upload', data)
            .then((response) => {
                toast.success('Upload Success');
                onSuccess(response.data)
            })
            .catch((e) => {
                toast.error('Upload Error')
            })
    };

    return (
        <form method="post" action="#" id="#" onSubmit={onSubmit}>
            <div className="form-group files">
                <label>Upload Your File </label>
                <input type="file"
                       onChange={onInputChange}
                       className="form-control"
                       multiple/>

            </div>

            <button>Submit</button>
        </form>
    )
};