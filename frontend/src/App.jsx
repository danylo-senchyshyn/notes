import React, { useEffect, useState } from 'react';

const API_URL = 'http://localhost:8080/api/notes';

function App() {
    const [notes, setNotes] = useState([]);
    const [form, setForm] = useState({ title: '', content: '' });
    const [editingId, setEditingId] = useState(null);

    const [searchInput, setSearchInput] = useState('');
    const [filterDate, setFilterDate] = useState('');

    useEffect(() => {
        fetchNotes();
    }, []);

    function fetchNotes() {
        fetch(API_URL)
            .then(res => res.json())
            .then(data => setNotes(data))
            .catch(console.error);
    }

    function handleChange(e) {
        setForm({ ...form, [e.target.name]: e.target.value });
    }

    function handleSubmit(e) {
        e.preventDefault();
        if (!form.title.trim() || !form.content.trim()) {
            alert('Заполните все поля');
            return;
        }

        const method = editingId ? 'PUT' : 'POST';
        const url = editingId ? `${API_URL}/${editingId}` : API_URL;

        fetch(url, {
            method,
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(form),
        })
            .then(res => {
                if (!res.ok) throw new Error('Ошибка при сохранении заметки');
                setForm({ title: '', content: '' });
                setEditingId(null);
                fetchNotes();
            })
            .catch(console.error);
    }

    function handleEdit(note) {
        setForm({ title: note.title, content: note.content });
        setEditingId(note.id);
    }

    function handleDelete(id) {
        if (!window.confirm('Удалить заметку?')) return;
        fetch(`${API_URL}/${id}`, { method: 'DELETE' })
            .then(res => {
                if (!res.ok) throw new Error('Ошибка при удалении заметки');
                fetchNotes();
            })
            .catch(console.error);
    }

    function handleCancel() {
        setForm({ title: '', content: '' });
        setEditingId(null);
    }

    // Функция поиска по заголовку при нажатии кнопки
    function performSearch() {
        if (!searchInput.trim()) {
            fetchNotes();
            return;
        }

        fetch(`${API_URL}/search?query=${encodeURIComponent(searchInput)}`)
            .then(res => res.json())
            .then(setNotes)
            .catch(console.error);

        setSearch(searchInput);
    }

    // Функция фильтрации по дате при нажатии кнопки
    function performFilterByDate() {
        if (!filterDate) {
            fetchNotes();
            return;
        }

        fetch(`${API_URL}/filter?date=${encodeURIComponent(filterDate)}`)
            .then(res => res.json())
            .then(setNotes)
            .catch(console.error);
    }

    return (
        <div style={{ maxWidth: 600, margin: 'auto', padding: 20 }}>
            <h1>Notes App</h1>

            <form onSubmit={handleSubmit} style={{ marginBottom: 20 }}>
                {/* Поиск по заголовку */}
                <input
                    type="text"
                    placeholder="🔍 Поиск по заголовку..."
                    value={searchInput}
                    onChange={(e) => setSearchInput(e.target.value)}
                    style={{
                        padding: '8px',
                        borderRadius: '5px',
                        border: '1px solid #ccc',
                        fontSize: '16px',
                        marginBottom: 8,
                        width: '80%'
                    }}
                />
                <button
                    type="button"
                    onClick={performSearch}
                    style={{ marginLeft: 8, padding: '8px 12px' }}
                >
                    Искать
                </button>

                {/* Фильтр по дате */}
                <div style={{ marginTop: 10, marginBottom: 10 }}>
                    <input
                        type="date"
                        value={filterDate}
                        onChange={(e) => setFilterDate(e.target.value)}
                        style={{
                            padding: '8px',
                            borderRadius: '5px',
                            border: '1px solid #ccc',
                            fontSize: '16px',
                            width: '60%',
                        }}
                    />
                    <button
                        type="button"
                        onClick={performFilterByDate}
                        style={{ marginLeft: 8, padding: '8px 12px' }}
                    >
                        Отфильтровать
                    </button>
                </div>

                {/* Поля для создания/редактирования заметки */}
                <input
                    name="title"
                    value={form.title}
                    onChange={handleChange}
                    placeholder="Заголовок"
                    style={{ width: '100%', padding: 8, marginBottom: 8 }}
                />
                <textarea
                    name="content"
                    value={form.content}
                    onChange={handleChange}
                    placeholder="Текст заметки"
                    style={{ width: '100%', padding: 8, height: 100, marginBottom: 8 }}
                />
                <button type="submit">{editingId ? 'Сохранить' : 'Добавить'}</button>
                {editingId && (
                    <button type="button" onClick={handleCancel} style={{ marginLeft: 10 }}>
                        Отмена
                    </button>
                )}
            </form>

            <ul style={{ listStyle: 'none', padding: 0 }}>
                {notes.map(note => (
                    <li key={note.id} style={{ marginBottom: 15, borderBottom: '1px solid #ccc', paddingBottom: 10 }}>
                        <h3>{note.title}</h3>
                        <p>{note.content}</p>
                        <button onClick={() => handleEdit(note)}>Редактировать</button>
                        <button onClick={() => handleDelete(note.id)} style={{ marginLeft: 10 }}>
                            Удалить
                        </button>
                    </li>
                ))}
            </ul>
        </div>
    );
}

export default App;