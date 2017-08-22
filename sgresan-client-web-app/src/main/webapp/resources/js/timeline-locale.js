if (typeof links === 'undefined') {
    links = {};
    links.locales = {};
} else if (typeof links.locales === 'undefined') {
    links.locales = {};
}

links.locales['tes'] = {
    'MONTHS': ["Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"],
    'MONTHS_SHORT': ["1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"],
    'DAYS': ["Domingo", "Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado"],
    'DAYS_SHORT': ["D", "L", "M", "X", "J", "V", "S"],
    'ZOOM_IN': "Acercarse",
    'ZOOM_OUT': "Alejarse",
    'MOVE_LEFT': "Izquierda",
    'MOVE_RIGHT': "Derecha",
    'NEW': "Nuevo",
    'CREATE_NEW_EVENT': "Crear una nueva acción"
};